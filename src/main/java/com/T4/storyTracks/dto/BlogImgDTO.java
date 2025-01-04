package com.T4.storyTracks.dto;

import com.T4.storyTracks.entity.BlogImgEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogImgDTO {

    private Long imgId;
    private Long postId;
    private String geoLat;
    private String geoLong;
    private String imgPath;
    private LocalDateTime imgDtm;
    private LocalDateTime rgstDTm;
    private String thumbYn;
//    private BlogPostEntity blogPost;

    public BlogImgDTO(Long imgId, Long postId, String geoLat, String geoLong, String imgPath, LocalDateTime imgDtm, String thumbYn) {
        this.imgId = imgId;
        this.postId = postId;
        this.geoLat = geoLat;
        this.geoLong = geoLong;
        this.imgPath = imgPath;
        this.imgDtm = imgDtm;
        this.rgstDTm = rgstDTm;
        this.thumbYn = thumbYn;
    }

    public static BlogImgDTO toImgDTO(BlogImgEntity blogImgEntity) {
        BlogImgDTO blogImgDTO = new BlogImgDTO();
        blogImgDTO.setImgId(blogImgEntity.getImgId());
        blogImgDTO.setPostId(blogImgEntity.getBlogPost().getPostId());
        blogImgDTO.setGeoLat(blogImgEntity.getGeoLat());
        blogImgDTO.setGeoLong(blogImgEntity.getGeoLong());
        blogImgDTO.setImgPath(blogImgEntity.getImgPath());
        blogImgDTO.setImgDtm(blogImgEntity.getImgDtm());
        blogImgDTO.setRgstDTm(blogImgEntity.getRgstDTm());

        return blogImgDTO;
    }
}
