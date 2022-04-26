package com.javagang.rdcoursemanagementplatform.controller;

import com.javagang.rdcoursemanagementplatform.service.S3BucketStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/storage")
@RequiredArgsConstructor
public class S3BucketStorageController {

  private final S3BucketStorageService service;

  @GetMapping
  public ResponseEntity<List<String>> getListOfFiles() {
    return new ResponseEntity<>(service.listFiles(), HttpStatus.OK);
  }

  @PostMapping("/upload")
  public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
    service.uploadFile(file.getOriginalFilename(), file);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/download")
  public ResponseEntity<byte[]> downloadFile(@RequestParam("filename") String filename) {
    ByteArrayOutputStream downloadInputStream = service.downloadFile(filename);
    return ResponseEntity.ok()
        .contentType(contentType(filename))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
        .body(downloadInputStream.toByteArray());
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<Void> deleteFile(@RequestParam("filename") String filename) {
    service.deleteFile(filename);
    return ResponseEntity.accepted().build();
  }

  private MediaType contentType(String filename) {
    String[] fileArrSplit = filename.split("\\.");
    String fileExtension = fileArrSplit[fileArrSplit.length - 1];
    switch (fileExtension) {
      case "txt":
        return MediaType.TEXT_PLAIN;
      case "png":
        return MediaType.IMAGE_PNG;
      case "jpg":
        return MediaType.IMAGE_JPEG;
      default:
        return MediaType.APPLICATION_OCTET_STREAM;
    }
  }
}
