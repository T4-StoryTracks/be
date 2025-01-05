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
import java.util.*;

@Controller
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

    //프론트에서 위도 경도, 사용자 글 받기
    @PostMapping("/save")
    public ResponseEntity<BlogImgEntity> saveLocation(@RequestBody BlogImgDTO request) {
        BlogImgEntity savedLocation = blogService.saveLagLong(request);
        return ResponseEntity.ok(savedLocation);
    }

    //생성한 리스트 세개 프론트로 보내기
    @GetMapping("/save/{postId}")
    public ResponseEntity<List<String>> aiGenTextList(@PathVariable(name = "postId") Long postId) {
        BlogPostEntity creatingPost = blogRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Blog post with ID " + postId + " not found."));
        String ogText = creatingPost.getOgText();
        long geoLag = 12;
        long geoLong = 38;
        int[] length = {200, 350, 450};
        List<String> aiGenTextList = new ArrayList<>();
        for (int len : length) {
            aiGenTextList.add(blogService.genText(ogText, geoLag, geoLong, len));
        }
        return ResponseEntity.ok(aiGenTextList);
    }
}
