package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogListPostDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogImgRepository;
import com.T4.storyTracks.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogImgRepository blogImgRepository;
    public List<BlogListPostDTO> findAll() {
//        Optional<BlogImgEntity> thumbImgEntity = new
        List<BlogPostEntity> blogPostEntityList= blogRepository.findAll();
        List<BlogListPostDTO> blogPostDTOList =new ArrayList<>();
        for (BlogPostEntity postEntity: blogPostEntityList) {
            //thumbImgEntity = blogImgRepository.findThumbImg(postEntity.getPostId(), "Y");
//            thumbImgEntity = blogImgRepository.findByPostIdAndThumbYn(postEntity.getPostId(), "Y");
            blogPostDTOList.add(BlogListPostDTO.toPostListDTO(postEntity, blogImgRepository.findByBlogPostPostIdAndThumbYn(postEntity.getPostId(), "Y")
                    .orElseThrow(() -> new IllegalArgumentException("blogImgEntity no thumbImg"))));
//            blogImgRepository.findByPostIdAndThumbYn(postEntity.getPostId(), "Y");

        }
        return blogPostDTOList;
    }

    public List<BlogImgDTO> getBlogImgList(Long postId) {
        List<BlogImgEntity> imgEntityList = blogImgRepository.findByBlogPostPostId(postId);
        if (imgEntityList.isEmpty()) {
            throw new IllegalArgumentException("no imgEntityList");
        }

        List<BlogImgDTO> imgDTOList = new ArrayList<>();
        imgEntityList.stream().forEach(x -> imgDTOList.add(BlogImgDTO.toImgDTO(x)));
        return imgDTOList;
    }

    public BlogPostDTO getBlogPostOne(Long postId) {
        //getBlogImgList(postId)
        Optional<BlogPostEntity> postEntity = blogRepository.findById(postId);
        if (!postEntity.isPresent()) {
            throw new IllegalArgumentException("no blogPost page");
        }

        // 이미지 리스트 가져오기
        List<BlogImgEntity> imgEntityList = blogImgRepository.findByBlogPostPostId(postId);
        if (imgEntityList.isEmpty()) {
            throw new IllegalArgumentException("no imgEntityList");
        }
        List<BlogImgDTO> imgDTOList = new ArrayList<>();
        imgEntityList.stream().forEach(x -> imgDTOList.add(BlogImgDTO.toImgDTO(x)));
        BlogPostDTO postDTO = BlogPostDTO.toPostDTO(postEntity.get(),imgDTOList);

        return postDTO;
    }
//    public List<BlogImgDTO> findImgAll() {
//        List<BlogImgEntity> imgEntityList = blogImgRepository.find();
//        List<BlogImgDTO> blogImgDTOList = new ArrayList<>();
//        for (BlogImgEntity imgEntity : imgEntityList) {
//            blogImgDTOList.add(BlogImgDTO.toImgDTO(imgEntity));
//        }
//        return blogImgDTOList;
//    }
}
