package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

//    @GetMapping("/list")
//    public String findAll(Model model) {
//        List<BlogPostDTO> blogDTOList = blogService.findAll();
//        model.addAttribute("blogPostList", blogDTOList);
//        System.out.println("blogPostList"+blogDTOList);
//        return "list";
//    }
}
