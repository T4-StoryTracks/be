package com.T4.storyTracks.controller;


import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogListPostDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class HomeController {
    private final BlogService blogService;

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        List<BlogListPostDTO> blogPostDTOList = blogService.findAll();
        return new ResponseEntity<>(blogPostDTOList, HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> findOne(@PathVariable Long postId, Model model) {
        BlogPostDTO imgDto = blogService.getBlogPostOne(postId);
        return new ResponseEntity<>(imgDto, HttpStatus.OK);
    }

    @PostMapping("/update/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, Model model) {

        return new ResponseEntity<>("update success", HttpStatus.OK);
    }
}
