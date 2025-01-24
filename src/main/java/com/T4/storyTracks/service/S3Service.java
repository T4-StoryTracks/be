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

@Service
@RequiredArgsConstructor
public class S3Service {

    private final String bucketName = "my-unique-bucket-name-143-adf-ggads2245";
    private final String region = "us-west-2";

    private S3Client getS3Client() {

        try{
            // 환경 변수 읽기
            String awsAccessKeyId = System.getenv("AWS_ACCESS_KEY_ID");
            String awsSecretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY");

            // null 체크
            if (awsAccessKeyId == null || awsSecretAccessKey == null) {
                throw new IllegalArgumentException("Environment variable 'AWS_ACCESS_KEY_ID' or 'AWS_SECRET_ACCESS_KEY' is not set or is null.");
            } else {
                return S3Client.builder()
                        .region(Region.of(region))
                        .credentialsProvider(StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(awsAccessKeyId, awsSecretAccessKey)))
                        .build();
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }



        /*
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            return S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY_ID"), System.getenv("AWS_SECRET_KEY_ID"))))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
         */
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
    public String getFileUrl(String fileName) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/uploadtest1/" + fileName;
    }

}