package com.file.upload.fileupload;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<CSVData,String> {

}
