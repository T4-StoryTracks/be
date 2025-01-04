package com.T4.storyTracks.controller;

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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BlogService blogService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<BlogPostDTO> blogPostDTOList = blogService.findAll();
        return new ResponseEntity<>(blogPostDTOList, HttpStatus.OK);
    }
}
