package com.file.download.filedownload;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class FiledownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiledownloadApplication.class, args);
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
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
		
		String fileName="D:\\TCS\\first project\\durga\\csvData.csv";
		
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

}
