//package com.T4.storyTracks.service;
//
//import java.io.File;
//import java.io.IOException;
//
//import com.T4.storyTracks.entity.BlogImgEntity;
//import com.T4.storyTracks.repository.BlogImgRepository;
//import com.drew.imaging.ImageMetadataReader;
//import com.drew.imaging.ImageProcessingException;
//import com.drew.metadata.Metadata;
//import com.drew.metadata.exif.GpsDirectory;
//import org.springframework.stereotype.Service;
//
//@Service
////위도 경도 추출 시도 - 프론트에서 추출한 거 받기로
//public class ImageService {
////    String imageLocation = "/Users/kimnajin/Desktop/codefiles/T4-StoryTracks/be/src/main/resources/static/img.jpeg";
////    File image = new File(imageLocation);
////    Metadata metadata = ImageMetadataReader.readMetadata(image);
////
////    public void printCoordinate() {
////        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
////        if(hasGpsInformation(gpsDirectory)) {
////            double longitude = gpsDirectory.getGeoLocation().getLongitude();
////            double latitude = gpsDirectory.getGeoLocation().getLatitude();
////            System.out.println("위도 : " + latitude + ", 경도 : " + longitude);
////        }
////    }
////
////    private boolean hasGpsInformation(GpsDirectory gpsDirectory) {
////        return gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE);
////
////    }
////    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
////    public void processImageWithGeoData(Long imageId) {
////        // 이미지 파일 경로를 통해 메타데이터 추출
////        String fileScope = "static/img.jpeg";
////
////        // 메타데이터 추출
////        File file = new File(fileScope);
////        Metadata metadata = ImageMetadataReader.readMetadata(file);
////        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
////
////        // 위도, 경도 추출
////        if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {
////            double latitude = gpsDirectory.getGeoLocation().getLatitude();
////            double longitude = gpsDirectory.getGeoLocation().getLongitude();
////
////            // Geo 데이터를 엔티티에 저장
////            BlogImgEntity BlogImgEntity = BlogImgRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
////            BlogImgEntity.setGeoLat(latitude);
////            BlogImgEntity.setGeoLong(longitude);
////
////            // 이미지 엔티티 저장
////            BlogImgRepository.save(BlogImgEntity);
////        }
////    }
//}
