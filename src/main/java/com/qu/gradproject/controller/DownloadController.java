package com.qu.gradproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qu.gradproject.entity.FilesEntity;
import com.qu.gradproject.service.FilesService;

@RestController
public class DownloadController {
	
	@Autowired
	FilesService filesService;
	
	@Value("${directory}")
    private String directory;
	
	@GetMapping("/downloadZip/{id}")
    public void downloadFile(@PathVariable (value="id") long id ,HttpServletResponse response) {
		FilesEntity filesEntity=filesService.findFileById(id);
         
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+filesEntity.getFileName()+".zip");
        response.setStatus(HttpServletResponse.SC_OK);
        
        
        List<String> fileNames=new ArrayList<>();
        String downloadDIR = directory +filesEntity.getGroupsEntity().getGroupNumber()+"\\"+filesEntity.getUploadType()+"\\"+filesEntity.getFileName();
        
        fileNames.add(downloadDIR);
        
       
       
        
       // System.out.println("############# file size ###########" + fileNames.size());

        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            for (String file : fileNames) {
                FileSystemResource resource = new FileSystemResource(file);

                ZipEntry e = new ZipEntry(resource.getFilename());
                // Configure the zip entry, the properties of the file
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                // etc.
                zippedOut.putNextEntry(e);
                // And the content of the resource:
                StreamUtils.copy(resource.getInputStream(), zippedOut);
                zippedOut.closeEntry();
            }
            zippedOut.finish();
        } catch (Exception e) {
            // Exception handling goes here
        }
    }

}
