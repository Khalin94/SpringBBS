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
//import org.community.domain.JobsBoardAttachVO;
//import org.community.mapper.JobsBoardAttachMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class JobsAttachFileCheckTask {
//	private JobsBoardAttachMapper mapper;
//	
//	@Autowired
//	private void setJobsBoardAttachMapper(JobsBoardAttachMapper mapper) {
//		this.mapper = mapper;
//	}
//	
//	private String getFolderYesterDay() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
//		String str = sdf.format(cal.getTime());
//		
//		return str.replace("-", File.separator);
//	}
//	
//	@Scheduled(cron="0 * * * * *")
//	public void checkFiles() throws Exception{
//		List<JobsBoardAttachVO> fileList = mapper.getOldFiles();
//		
//		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid()+"_"+ vo.getFileName())).collect(Collectors.toList());
//		log.info("fileListPaths : " + fileListPaths.toString());
//		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_"+vo.getUuid()+"_"+vo.getFileName())).forEach(p -> fileListPaths.add(p));
//
//		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
//		
//		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
//		
//		for(File file : removeFiles) {
//				file.delete();
//			
//		}
//	}
//
//}
