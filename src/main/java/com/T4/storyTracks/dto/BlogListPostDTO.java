package com.T4.storyTracks.dto;

import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogListPostDTO {
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
//    private Long thumbImgId;
//    private String thumbImgPath;
//    private String thumbGeoLat;
//    private String thumbGeoLong;
    private Map<String, String> thumbHash = new HashMap<>(4);

    public BlogListPostDTO(Long postId, String title, String ogText, String aiGenText, String password, LocalDateTime rgstDtm) {
        this.postId = postId;
        this.title = title;
        this.ogText = ogText;
        this.aiGenText = aiGenText;
        this.password = password;
        this.rgstDtm = rgstDtm;
        //this.chngDtm = chngDtm;
    }

    @Description(value="전체 조회 시 대표이미지 정보만 가져옴")
    public static BlogListPostDTO toPostListDTO(BlogPostEntity blogPostEntity, BlogImgEntity thumbImgEntity) {
//        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddkkmmss");

        BlogListPostDTO blogPostDTO = new BlogListPostDTO();
        blogPostDTO.setPostId(blogPostEntity.getPostId());
        blogPostDTO.setTitle(blogPostEntity.getTitle());
        blogPostDTO.setOgText(blogPostEntity.getOgText());
        blogPostDTO.setAiGenText(blogPostEntity.getAiGenText());
        blogPostDTO.setPassword(blogPostEntity.getPassword());
        blogPostDTO.setRgstDtm(blogPostEntity.getRgstDtm());
        //blogPostDTO.setImgEntityList(blogPostEntity.getImgEntityList());

        // 대표이미지 정보 설정
//        blogPostDTO.setThumbImgId(thumbImgEntity.getImgId());
//        blogPostDTO.setThumbImgPath(thumbImgEntity.getImgPath());
//        blogPostDTO.setThumbGeoLat(thumbImgEntity.getGeoLat());
//        blogPostDTO.setThumbGeoLong(thumbImgEntity.getGeoLong());
        Map<String, String> tmp = new HashMap<>(4);
        tmp.put("thumbImgId",thumbImgEntity.getImgId().toString());
        tmp.put("thumbImgPath",thumbImgEntity.getImgPath());
        tmp.put("thumbGeoLat",thumbImgEntity.getGeoLat());
        tmp.put("thumbGeoLong",thumbImgEntity.getGeoLong());
        blogPostDTO.setThumbHash(tmp);

        return blogPostDTO;
    }
}
