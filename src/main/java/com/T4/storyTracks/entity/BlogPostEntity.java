package com.T4.storyTracks.entity;

import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogImgEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
public class BlogPostEntity {

    @Id
    @Column(nullable = false, name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 100, nullable = false, name = "title")
    private String title;

    @Lob
    @Column(name="og_text")
    private String ogText;

    @Lob
    @Column(name="ai_gen_text")
    private String aiGenText;

    @Column(length = 64, nullable = false, name="password")
    private String password;

    @Column(name="rgst_dtm")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rgstDtm;

    @Column(name="chng_dtm")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime chngDtm;

    @OneToMany(mappedBy = "blogPost")
    private List<BlogImgEntity> imgEntityList = new ArrayList<>();

    public void addImgEntityList(BlogImgEntity blogImg) {
        this.imgEntityList.add(blogImg);
        blogImg.setBlogPost(this);
    }


    public static BlogPostEntity toPostEntity(BlogPostDTO postDTO) {
        BlogPostEntity postEntity = new BlogPostEntity();

        postEntity.setTitle(postDTO.getTitle());
        postEntity.setPassword("password");
        postEntity.setOgText(postDTO.getOgText());
        postEntity.setAiGenText(postDTO.getAiGenText());
        postEntity.setRgstDtm(LocalDateTime.now());
        postEntity.setChngDtm(LocalDateTime.now());

        return postEntity;
    }
}
