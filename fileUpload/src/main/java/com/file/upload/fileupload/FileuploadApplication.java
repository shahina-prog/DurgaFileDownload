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
public class FileuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileuploadApplication.class, args);
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

}
