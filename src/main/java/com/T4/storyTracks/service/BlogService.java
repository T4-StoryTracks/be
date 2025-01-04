package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogImgRepository;
import com.T4.storyTracks.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogImgRepository blogImgRepository;

    public List<BlogPostDTO> findAll() {
        List<BlogPostEntity> blogPostEntityList= blogRepository.findAll();
        List<BlogPostDTO> blogPostDTOList =new ArrayList<>();
        for (BlogPostEntity postEntity: blogPostEntityList) {
            blogPostDTOList.add(BlogPostDTO.toPostDTO(postEntity));
        }
        return blogPostDTOList;
    }

    public BlogImgEntity saveLagLong(BlogImgDTO request) {
        BlogImgEntity location = new BlogImgEntity();
        location.setGeoLat(request.getGeoLat());
        location.setGeoLong(request.getGeoLong());
        return blogImgRepository.save(location);
    }
}
