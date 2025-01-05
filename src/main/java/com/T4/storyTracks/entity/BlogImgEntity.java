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
@Table(name = "imgs")
public class BlogImgEntity {
    @Id
    @Column(nullable = false, name="img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @Column(name="geo_lat", length = 10)
    private String geoLat;

    @Column(name="geo_long", length = 10)
    private String geoLong;

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

//    @Column(name="post_id")
//    private String postId;

    @ManyToOne
    @JoinColumn(name="post_Id")
    private BlogPostEntity blogPost;
}