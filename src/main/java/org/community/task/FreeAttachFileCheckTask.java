//package org.community.task;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.community.domain.FreeBoardAttachVO;
//import org.community.mapper.FreeBoardAttachMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FreeAttachFileCheckTask {
//	Logger log = LoggerFactory.getLogger(FreeAttachFileCheckTask.class);
//	
//	private FreeBoardAttachMapper mapper;
//	
//	@Autowired
//	private void setFreeBoardAttachMapper(FreeBoardAttachMapper mapper) {
//		this.mapper = mapper;
//	}
//	
//	private String getFolderYesterDay() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		
//		cal.add(Calendar.DATE, -1);
//		
//		String str = sdf.format(cal.getTime());
//
//		return str.replace("-", File.separator);
//	}
//	
//	@Scheduled(cron = "0 * * * * *")
//	public void checkFiles() throws Exception{
//		log.warn("check Files Task run");
//		log.warn("=============================");
//		
//		List<FreeBoardAttachVO> fileList = mapper.getOldFiles();
//		
//		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("C:\\upload",
//				vo.getUploadPath(), vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());
//		
//		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_"+vo.getUuid()+"_"+vo.getFileName())).forEach(p -> fileListPaths.add(p));
//		
//		log.warn("==============================");
//		
//		fileListPaths.forEach(p -> log.warn(p.toString()));
//		
//		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
//		
//		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
//		
//		log.warn("-------------------------------");
//		
//		for(File file : removeFiles) {
//				log.warn(file.getAbsolutePath());
//				file.delete();
//		}
//	}
//
//}
