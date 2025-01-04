package com.T4.storyTracks.dto;

import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDTO {
    private Long postId;
    private String title;
    private String ogText;
    private String aiGenText;
    private String password;
//    private LocalDateTime rgstDtm;
//    private LocalDateTime chngDtm;
    private String rgstDtm;
    private String chngDtm;
//    private List<BlogImgEntity> imgEntityList = new ArrayList<>();

    public BlogPostDTO(Long postId, String title, String ogText, String aiGenText, String password, String rgstDtm) {
        this.postId = postId;
        this.title = title;
        this.ogText = ogText;
        this.aiGenText = aiGenText;
        this.password = password;
        this.rgstDtm = rgstDtm;
    }

    public static BlogPostDTO toPostDTO(BlogPostEntity blogPostEntity) {
        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setPostId(blogPostEntity.getPostId());
        blogPostDTO.setTitle(blogPostEntity.getTitle());
        blogPostDTO.setOgText(blogPostEntity.getOgText());
        blogPostDTO.setAiGenText(blogPostEntity.getAiGenText());
        blogPostDTO.setPassword(blogPostEntity.getPassword());
        blogPostDTO.setRgstDtm(blogPostEntity.getRgstDtm().toString());

        return blogPostDTO;
    }
}
