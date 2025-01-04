package com.T4.storyTracks.entity;

import java.time.LocalDateTime;

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="img_id")
    private int imgId;
    @Column(name="geo_lat")
    private String geoLat;
    @Column(name="geo_long")
    private String geoLong;
    @Column(name="password")
    private String password;
    @Column(name="img_dtm")
    private LocalDateTime imgDtm;
    @Column(name="rgst_dtm")
    private LocalDateTime rgstDtm;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    @Column(name="thumb_yn")
    private boolean thumbYn;
}
