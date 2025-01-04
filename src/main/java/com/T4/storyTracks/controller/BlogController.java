package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogController {
    public final BlogService blogService;


}
