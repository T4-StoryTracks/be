package com.T4.storyTracks.repository;

import com.T4.storyTracks.entity.BlogPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogPostEntity, Long> {
    Optional<Object> findByPostId(Long postId);
}
