package com.T4.storyTracks.dto;

import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDtm;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime chngDtm;
//    private String rgstDtm;
//    private String chngDtm;
    //private List<BlogImgEntity> imgEntityList = new ArrayList<>();

    public BlogPostDTO(Long postId, String title, String ogText, String aiGenText, String password, LocalDateTime rgstDtm) {
        this.postId = postId;
        this.title = title;
        this.ogText = ogText;
        this.aiGenText = aiGenText;
        this.password = password;
        this.rgstDtm = rgstDtm;
        //this.chngDtm = chngDtm;
    }

    public static BlogPostDTO toPostDTO(BlogPostEntity blogPostEntity) {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddkkmmss");

        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setPostId(blogPostEntity.getPostId());
        blogPostDTO.setTitle(blogPostEntity.getTitle());
        blogPostDTO.setOgText(blogPostEntity.getOgText());
        blogPostDTO.setAiGenText(blogPostEntity.getAiGenText());
        blogPostDTO.setPassword(blogPostEntity.getPassword());
        blogPostDTO.setRgstDtm(blogPostEntity.getRgstDtm());
        //blogPostDTO.setImgEntityList(blogPostEntity.getImgEntityList());

        return blogPostDTO;
    }
}
