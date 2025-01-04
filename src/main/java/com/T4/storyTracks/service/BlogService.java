package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    public List<BlogPostDTO> findAll() {
        List<BlogPostEntity> blogPostEntityList= blogRepository.findAll();
        List<BlogPostDTO> blogPostDTOList =new ArrayList<>();
        for (BlogPostEntity postEntity: blogPostEntityList) {
            blogPostDTOList.add(BlogPostDTO.toPostDTO(postEntity));
        }
        return blogPostDTOList;
    }

}
