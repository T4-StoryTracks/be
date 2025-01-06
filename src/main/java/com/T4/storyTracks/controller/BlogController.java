package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.*;
import com.T4.storyTracks.repository.BlogRepository;
import com.T4.storyTracks.service.BlogService;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogRepository;
import com.T4.storyTracks.service.BlogService;
import com.T4.storyTracks.service.S3Service;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.invoker.HttpRequestValues;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    public final BlogRepository blogRepository;
    public final BlogService blogService;

    JsonMapper mapper = JsonMapper.builder().configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true).build();

    @PostMapping("/generate")
    public ResponseEntity<?> generateAIText(@RequestBody GenInputDTO genInputDTO) {
        Map<String, String> genAIList = new HashMap<>();
        ImgMetaDTO imgMetaDTO = genInputDTO.getImgMetaDTO();
//        for (ImgMetaDTO imgMetaDTO: genInputDTO.getImgInfoList()) {
//            //System.out.println(imgMetaDTO.getGeoLat()+", "+imgMetaDTO.getGeoLong());
//            genAIListAll.add(blogService.createPost(imgMetaDTO, genInputDTO.getOgText()));
//        }
        genAIList = blogService.createPost(imgMetaDTO, genInputDTO.getOgText());
        return new ResponseEntity<>(genAIList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public void savePost(@RequestBody NewPostDTO newPostDTO) {
        BlogPostDTO postDTO = new BlogPostDTO(newPostDTO.getTitle(), newPostDTO.getOgText(), newPostDTO.getAiGenText());
        Long postId = blogService.savePost(postDTO);
        String imgUrl = "";
        List<Map<String, String>> imgSaveList = new ArrayList<>();
        for (BlogImgDTO imgDTO : newPostDTO.getImgSaveList()) {
            imgUrl = blogService.saveImgS3(imgDTO, postId);
            blogService.saveImgList(imgDTO, postId, imgUrl);
        }


//        return new ResponseEntity<>(postId, HttpStatus.OK);
    }


//    //프론트에서 위도 경도, 사용자 글 받기 - generate content 버튼 클릭 시 ?
//    @PostMapping("/saveImg")
//    public ResponseEntity<BlogImgEntity> saveLocation(@RequestBody BlogImgDTO request) {
//        request.setRgstDtm(LocalDateTime.now());
//        BlogImgEntity savedLocation = blogService.saveLagLong(request);
//        return ResponseEntity.ok(savedLocation);
//    }
//
//    //글 내용 - generate content 버튼 클릭 시 ?
//    @PostMapping("/savePost")
//    public ResponseEntity<BlogPostEntity> savePost(@RequestBody BlogPostEntity request) {
//        BlogPostEntity post = blogService.savePost(request);
//        return ResponseEntity.ok(post);
//    }
//
//    //생성한 리스트 세개 프론트로 보내기 - generate content/ change description 클릭 시
//    @GetMapping("/aiGen/{postId}")
//    public ResponseEntity<List<String>> aiGenTextList(@PathVariable(name = "postId") Long postId) {
//        BlogPostEntity creatingPost = blogRepository.findById(postId)
//                .orElseThrow(() -> new EntityNotFoundException("Blog post with ID " + postId + " not found."));
//        String ogText = creatingPost.getOgText();
//        long geoLag = 12;
//        long geoLong = 38;
//        int[] length = {200, 350, 450}; //프롬프트에 요청한 글 길이
//        List<String> aiGenTextList = new ArrayList<>();
//        for (int len : length) {
//            aiGenTextList.add(blogService.genText(ogText, geoLag, geoLong, len));
//        }
        genAIList = blogService.createPost(imgMetaDTO, genInputDTO.getOgText());
        return new ResponseEntity<>(genAIList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public void savePost(@RequestBody NewPostDTO newPostDTO) {
        BlogPostDTO postDTO = new BlogPostDTO(newPostDTO.getTitle(), newPostDTO.getOgText(), newPostDTO.getAiGenText());
        Long postId = blogService.savePost(postDTO);
        String imgUrl = "";
        List<Map<String, String>> imgSaveList = new ArrayList<>();
        for (BlogImgDTO imgDTO : newPostDTO.getImgSaveList()) {
            imgUrl = blogService.saveImgS3(imgDTO, postId);
            blogService.saveImgList(imgDTO, postId, imgUrl);
        }


//        return new ResponseEntity<>(postId, HttpStatus.OK);
    }

}
