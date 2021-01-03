package com.example.sweater.repos;

import com.example.sweater.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepo extends JpaRepository<Forum, Long> {
}
