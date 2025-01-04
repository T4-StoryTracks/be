package com.T4.storyTracks.controller;

import com.T4.storyTracks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BlogService blogService;
    @GetMapping("/")
    public String getAllUsers() {
        var ing = blogService.getAllUsers();
        ing.forEach(System.out::println);
        return "index";
    }
}
