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
import org.springframework.context.annotation.Description;

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
    private Long thumbImgId;
    private String thumbImgPath;
    private String thumbGeoLat;
    private String thumbGeoLong;

    public BlogPostDTO(Long postId, String title, String ogText, String aiGenText, String password, LocalDateTime rgstDtm) {
        this.postId = postId;
        this.title = title;
        this.ogText = ogText;
        this.aiGenText = aiGenText;
        this.password = password;
        this.rgstDtm = rgstDtm;
        //this.chngDtm = chngDtm;
    }

    @Description(value="전체 조회 시 대표이미지 정보만 가져옴")
    public static BlogPostDTO toPostListDTO(BlogPostEntity blogPostEntity, BlogImgEntity thumbImgEntity) {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddkkmmss");

        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setPostId(blogPostEntity.getPostId());
        blogPostDTO.setTitle(blogPostEntity.getTitle());
        blogPostDTO.setOgText(blogPostEntity.getOgText());
        blogPostDTO.setAiGenText(blogPostEntity.getAiGenText());
        blogPostDTO.setPassword(blogPostEntity.getPassword());
        blogPostDTO.setRgstDtm(blogPostEntity.getRgstDtm());
        //blogPostDTO.setImgEntityList(blogPostEntity.getImgEntityList());

        // 대표이미지 정보 설정
        blogPostDTO.setThumbImgId(thumbImgEntity.getImgId());
        blogPostDTO.setThumbImgPath(thumbImgEntity.getImgPath());
        blogPostDTO.setThumbGeoLat(thumbImgEntity.getGeoLat());
        blogPostDTO.setThumbGeoLong(thumbImgEntity.getGeoLong());
        return blogPostDTO;
    }
}
