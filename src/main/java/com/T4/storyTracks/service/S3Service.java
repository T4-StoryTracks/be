package com.T4.storyTracks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final String bucketName = "my-unique-bucket-name-143-adf-ggads2245";
    private final String region = "us-west-2";
    private final String accessKey = "AKIA6GBMETCY6S4PW7E7";
    private final String secretKey = "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";

    private S3Client getS3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        System.out.println("uploadFile진입");
        String fileName = folderName + "/" + file.getOriginalFilename();
        S3Client s3Client = getS3Client();

        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile); // MultipartFile을 임시 파일로 변환

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .contentType(URLConnection.guessContentTypeFromName(file.getOriginalFilename()))
                        .build(),
                tempFile.toPath()
        );

        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
    }

    public void deleteFile(String fileName) {
        S3Client s3Client = getS3Client();
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build());
    }
}