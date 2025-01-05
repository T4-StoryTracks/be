package com.T4.storyTracks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tb_imgs")
public class BlogImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @Column(name="geo_lat")
    private Long geoLat;

    @Column(name="geo_long")
    private Long geoLong;

    @Column(name="img_path")
    private String imgPath;

    @Column(name="img_dtm")
    private LocalDateTime imgDtm;

    @Column(name="rgst_dtm")
//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rgstDTm;

    @Column(name="thumb_yn")
    private String thumbYn;

    @ManyToOne
    @JoinColumn(name="postId")
    private BlogPostEntity blogPost;
}
