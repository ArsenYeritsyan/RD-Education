package com.javagang.rdcoursemanagementplatform.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.javagang.rdcoursemanagementplatform.exception.AmazonClientServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

  public void uploadFile(String keyName, MultipartFile file) {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());
      s3client.putObject(defaultBucket, keyName, file.getInputStream(), metadata);
    } catch (IOException ioe) {
      throw new AmazonClientServiceException("IOException: " + ioe.getMessage());
    } catch (AmazonServiceException serviceException) {
      throw new AmazonClientServiceException("AmazonServiceException: " + serviceException.getMessage());
    } catch (AmazonClientException clientException) {
      throw new AmazonClientServiceException("AmazonClientException Message: " + clientException.getMessage());
    }
  }

  public void deleteFile(String fileName) {
    s3client.deleteObject(defaultBucket, fileName);
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
      throw new AmazonClientServiceException("IOException: " + ioException.getMessage());
    } catch (AmazonServiceException serviceException) {
      throw new AmazonClientServiceException("AmazonServiceException Message:    " + serviceException.getMessage());
    } catch (AmazonClientException clientException) {
      throw new AmazonClientServiceException("AmazonClientException Message: " + clientException.getMessage());
    }
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
}
