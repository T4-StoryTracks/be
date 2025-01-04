package com.T4.storyTracks.controller;

import com.T4.storyTracks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequiredArgsConstructor
public class BlogController {
    public final BlogService blogService;

}
