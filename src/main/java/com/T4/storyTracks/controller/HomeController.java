package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BlogService blogService;

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BlogPostDTO> blogPostDTOList = blogService.findAll();
        for (BlogPostDTO blogDTO : blogPostDTOList) {
            System.out.println(blogDTO.getTitle());
        }
        return "index";
    }
}
