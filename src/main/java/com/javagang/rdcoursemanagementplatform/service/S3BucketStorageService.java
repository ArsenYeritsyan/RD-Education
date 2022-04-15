package com.javagang.rdcoursemanagementplatform.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3BucketStorageService {
  private final AmazonS3 s3client;

  @Value("${cloud.aws.s3.endpoint}")
  private String endpointUrl;

  @Value("${cloud.aws.s3.bucket}")
  private String defaultBucket;

  public String uploadFile(String keyName, MultipartFile file) {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());
      s3client.putObject(defaultBucket, keyName, file.getInputStream(), metadata);
      return "File uploaded: " + keyName;
    } catch (IOException ioe) {
      log.error("IOException: " + ioe.getMessage());
    } catch (AmazonServiceException serviceException) {
      log.info("AmazonServiceException: " + serviceException.getMessage());
      throw serviceException;
    } catch (AmazonClientException clientException) {
      log.info("AmazonClientException Message: " + clientException.getMessage());
      throw clientException;
    }
    return "File not uploaded: " + keyName;
  }

  public String deleteFile(final String fileName) {
    s3client.deleteObject(defaultBucket, fileName);
    return "Deleted File: " + fileName;
  }

  public ByteArrayOutputStream downloadFile(String keyName) {
    try {
      S3Object s3object = s3client.getObject(new GetObjectRequest(defaultBucket, keyName));

      InputStream is = s3object.getObjectContent();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      int len;
      byte[] buffer = new byte[4096];
      while ((len = is.read(buffer, 0, buffer.length)) != -1) {
        outputStream.write(buffer, 0, len);
      }

      return outputStream;
    } catch (IOException ioException) {
      log.error("IOException: " + ioException.getMessage());
    } catch (AmazonServiceException serviceException) {
      log.info("AmazonServiceException Message:    " + serviceException.getMessage());
      throw serviceException;
    } catch (AmazonClientException clientException) {
      log.info("AmazonClientException Message: " + clientException.getMessage());
      throw clientException;
    }
    return null;
  }

  public List<String> listFiles() {
    ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(defaultBucket);
    List<String> keys = new ArrayList<>();
    ObjectListing objects = s3client.listObjects(listObjectsRequest);
    while (true) {
      List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
      if (objectSummaries.isEmpty()) {
        break;
      }
      for (S3ObjectSummary item : objectSummaries) {
        if (!item.getKey().endsWith("/")) keys.add(item.getKey());
      }
      objects = s3client.listNextBatchOfObjects(objects);
    }
    return keys;
  }

  public void createFolder(String folderName) {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(0);
      InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
      PutObjectRequest putObjectRequest =
          new PutObjectRequest(defaultBucket, folderName + "/", emptyContent, metadata);
      s3client.putObject(putObjectRequest);
    } catch (Exception e) {
      log.info("Error AmazonS3Service " + e.getMessage());
    }
  }

  public void deleteFolder(String folderName) {
    try {
      List<S3ObjectSummary> fileList =
          s3client.listObjects(defaultBucket, folderName).getObjectSummaries();
      for (S3ObjectSummary file : fileList) {
        s3client.deleteObject(defaultBucket, file.getKey());
      }
      s3client.deleteObject(defaultBucket, folderName);
    } catch (Exception e) {
      log.info("Error AmazonS3Service " + e.getMessage());
    }
  }
}
