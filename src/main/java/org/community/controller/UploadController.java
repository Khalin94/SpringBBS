package org.community.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.community.domain.AttachBoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	
	Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("======================");
			log.info("Upload File Name : "+ multipartFile.getOriginalFilename());
			log.info("Upload File Size : "+ multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload Ajax");
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info("content Type : "+ contentType);
			return contentType.contains("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachBoardVO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		
		List<AttachBoardVO> list = new ArrayList<AttachBoardVO>();

		log.info("ajax post!");
		
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("uploadPath : "+ uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {

			AttachBoardVO attachVo = new AttachBoardVO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			log.info("======================");
			log.info("upload file name : "+ multipartFile.getOriginalFilename());
			log.info("upload file size : "+ multipartFile.getSize());
		
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			log.info("only file name : "+ uploadFileName);
			attachVo.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_"+ uploadFileName;

			//File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachVo.setUuid(uuid.toString());
				attachVo.setUploadPath(uploadFolderPath);
				
				log.info("save File : "+ saveFile);
				
				if(checkImageType(saveFile)) {
					log.info("savefile : " +saveFile.toString());
					attachVo.setFileType(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
					log.info("thumbnail success");
				}				

				log.info("list add");
				list.add(attachVo);
			} catch (Exception e) {
				log.info("error");
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		
		log.info("upload Success!");
		return new ResponseEntity<List<AttachBoardVO>>(list, HttpStatus.OK);
	}  
	
	

	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
		log.info("fileName : " + fileName);
		Resource resource = new FileSystemResource("C:\\upload\\"+fileName);
		
		log.info("resouces : "+ resource);
		
		String resourceName = resource.getFilename();
		
		log.info("resouceName : "+ resourceName);
		
		String originalresourceName = resourceName.substring(resourceName.indexOf("_")+1);
		
		log.info(originalresourceName);
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			
			String downloadName = null;
			
			if(userAgent.contains("Trident")) {
				log.info("IE");
				downloadName = URLEncoder.encode(originalresourceName, "UTF-8").replaceAll("\\", " ");
			}else if(userAgent.contains("Edge")) {
				log.info("Edge");
				downloadName = URLEncoder.encode(originalresourceName, "UTF-8");
			}else {
				log.info("chrome");
				downloadName = new String(originalresourceName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			log.info("downloadName : " +downloadName);
			header.add("Content-Disposition", "attachment; filename=" + downloadName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("fileName : "+ fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\"+URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName : "+ largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName : "+fileName);
		
		File file = new File("c:\\upload\\"+fileName);
		
		log.info("file : "+ file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
