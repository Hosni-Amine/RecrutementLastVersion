package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class FileController {
    private static final String UPLOAD_DIR="./upload/";
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try{
            byte[] bytes=file.getBytes();
            Path path= Paths.get(UPLOAD_DIR+file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path,bytes);
            return new ResponseEntity<>("File uploaded successfully: "+file.getOriginalFilename(),HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get-image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename){
        try{
            Path file=Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource=new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                String mimeType=Files.probeContentType(file);
                MediaType mediaType=mimeType==null? MediaType.APPLICATION_OCTET_STREAM : MediaType.parseMediaType(mimeType);
                return ResponseEntity.ok().contentType(mediaType)
                        .body(resource);
            }
            else{
                throw  new RuntimeException("Could not read file");
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Error: "+e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
