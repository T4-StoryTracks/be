package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

//    public List<BlogPostDTO> findAll() {
//        return blogRepository.findAll();
//    }
}
