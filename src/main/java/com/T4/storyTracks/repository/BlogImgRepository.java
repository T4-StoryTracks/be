package com.T4.storyTracks.repository;

import com.T4.storyTracks.entity.BlogImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.desktop.QuitEvent;
import java.util.List;
import java.util.Optional;

public interface BlogImgRepository extends JpaRepository<BlogImgEntity, Long> {

    Optional<BlogImgEntity> findByBlogPostPostIdAndThumbYn(@Param("postId") Long postId, @Param("thumbYn") String thumbYn);

    List<BlogImgEntity> findByBlogPostPostId(@Param("postId") Long postId);

}
