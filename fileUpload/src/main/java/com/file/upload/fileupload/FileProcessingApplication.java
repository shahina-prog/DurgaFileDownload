package com.file.upload.fileupload;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@SpringBootApplication
@RestController
public class FileProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileProcessingApplication.class, args);
	}
	
	@RequestMapping(value="/downloadFile",method=RequestMethod.GET)
	public ResponseEntity<Object> downloadFile() throws IOException{
		
		CSVData csv=new CSVData();
		csv.setId("1");
		csv.setName("Shahina");
		csv.setNumber("5601");
		
		CSVData csv2=new CSVData();
		csv2.setId("2");
		csv2.setName("Durga");
		csv2.setNumber("5601");

		List<CSVData> csvData=new ArrayList<>();
		csvData.add(csv);
		csvData.add(csv2);
		
		StringBuilder fileContent=new StringBuilder("Id,Name,Number\n");
		for(CSVData csvd : csvData){
			
			fileContent.append(csvd.getId()).append(",").append(csvd.getName()).append(",").append(csvd.getNumber()).append(",").append("\n");
			
		}
		
		String fileName="src/main/resources/download.csv";
		
		FileWriter fileWriter=new FileWriter(fileName);
		fileWriter.write(fileContent.toString());
		fileWriter.flush();
		
		File file=new File(fileName);
		InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\'%s\'", file.getName().replace("'", "")));
		headers.add("Cache-Control","no-cache,no-store,must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		ResponseEntity<Object> resEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).body(resource);
		
		return resEntity;
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ResponseEntity<String> uploaddFile(@RequestParam("file")MultipartFile file) throws IOException{
		
		//file.transferTo(new File("D:\\TCS\\first project\\durga\\upload"+file.getOriginalFilename()));

		File convertFile=new File("D:\\TCS\\first project\\durga\\upload\\"+file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout=new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File is Upload Successfully",HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/copyFile")
	public ResponseEntity<String> copyFile() throws IOException{
		
		File src = new File("src/main/resources/upload.csv");
		File dest = new File("src/main/resources/uploadCopy.csv");
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(dest);

		byte[] buffer =new byte[1024];
		int length;
		
		while((length=fis.read(buffer))>0){
			fos.write(buffer, 0, length);
		}
				
		
		
		return new ResponseEntity<>("File Copy Successfully present in path fileupload/src/main/resources/uploadCopy.csv ",HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value="/deleteFile")
	public ResponseEntity<String> deleteFile() throws IOException{
		
		File deleteFile = new File("src/main/resources/uploadCopy.csv");
		deleteFile.delete();
		
		
		return new ResponseEntity<>("File deleted Successfully in path fileupload/src/main/resources/uploadCopy.csv ",HttpStatus.OK);
		
		
	}

}
