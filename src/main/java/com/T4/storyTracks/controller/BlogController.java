package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogRepository;
import com.T4.storyTracks.service.BlogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogController {
    public final BlogRepository blogRepository;
    public final BlogService blogService;

//    @PostMapping("/save")
//    @Description("저장")
//    public String save(@ModelAttribute BlogPostDTO blogPostDTO) throws IOException {
//        blogService.save(blogPostDTO);
//        return "index";
//    }

    //프론트에서 위도 경도, 사용자 글 받기 - generate content 버튼 클릭 시 ?
    @PostMapping("/saveImg")
    public ResponseEntity<BlogImgEntity> saveLocation(@RequestBody BlogImgDTO request) {
        request.setRgstDtm(LocalDateTime.now());
        BlogImgEntity savedLocation = blogService.saveLagLong(request);
        return ResponseEntity.ok(savedLocation);
    }

    //글 내용 - generate content 버튼 클릭 시 ?
    @PostMapping("/savePost")
    public ResponseEntity<BlogPostEntity> savePost(@RequestBody BlogPostEntity request) {
        BlogPostEntity post = blogService.savePost(request);
        return ResponseEntity.ok(post);
    }

    //생성한 리스트 세개 프론트로 보내기 - generate content/ change description 클릭 시
    @GetMapping("/aiGen/{postId}")
    public ResponseEntity<List<String>> aiGenTextList(@PathVariable(name = "postId") Long postId) {
        BlogPostEntity creatingPost = blogRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Blog post with ID " + postId + " not found."));
        String ogText = creatingPost.getOgText();
        long geoLag = 12;
        long geoLong = 38;
        int[] length = {200, 350, 450}; //프롬프트에 요청한 글 길이
        List<String> aiGenTextList = new ArrayList<>();
        for (int len : length) {
            aiGenTextList.add(blogService.genText(ogText, geoLag, geoLong, len));
        }
        return ResponseEntity.ok(aiGenTextList);
    }

    //사용자가 선택한 글/ 썸네일로 글 업로드 - Post 버튼 클릭 시
//    @PostMapping("")
//    @GetMapping("") //상세보기 화면

    //비번 일치하면 글 수정 및 삭제
}
