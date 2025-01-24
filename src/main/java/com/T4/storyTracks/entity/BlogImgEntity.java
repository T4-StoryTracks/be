package com.T4.storyTracks.entity;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "imgs")
public class BlogImgEntity {
    @Id
    @Column(nullable = false, name="img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;
    
    @Column(name="geo_lat", length = 20)
    private String geoLat;

    @Column(name="geo_long", length = 20)
    private String geoLong;

    @Column(name="img_path")
    private String imgPath;

    @Column(name="img_dtm")
    private OffsetDateTime imgDtm;

    @Column(name="rgst_dtm")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime rgstDTm;

    @Column(name="thumb_yn")
    private String thumbYn;

//    @Column(name="post_id")
//    private String postId;

    @ManyToOne
    @JoinColumn(name="post_Id")
    private BlogPostEntity blogPost;


    public static BlogImgEntity toImgEntity(BlogImgDTO imgDTO, BlogPostEntity postEntity) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        BlogImgEntity imgEntity = new BlogImgEntity();
        imgEntity.setImgPath(imgDTO.getFileName());
        imgEntity.setGeoLat(imgDTO.getGeoLat());
        imgEntity.setGeoLong(imgDTO.getGeoLong());
        imgEntity.setImgDtm(imgDTO.getImgDtm());
        imgEntity.setThumbYn(imgDTO.getThumbYn());
        imgEntity.setBlogPost(postEntity);
        return imgEntity;
    }
}