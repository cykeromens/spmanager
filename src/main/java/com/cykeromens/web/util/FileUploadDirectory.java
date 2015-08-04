/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author cykeromens
 */
public class FileUploadDirectory {
    
    
    public String prepareFile(MultipartFile file, String rootPath){
        String name = file.getOriginalFilename();
         try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String relativePath = "WEB-INF"+ File.separator + "resources" + File.separator + "images";
                File dir = new File(rootPath + File.separator + relativePath);
                if (!dir.exists()){
                    dir.mkdirs();
                }
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();//close stream
 
               
                return "You successfully uploaded file = " + name;
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
           
        } 
    }
}
