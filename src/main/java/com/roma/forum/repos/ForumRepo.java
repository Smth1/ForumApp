package com.roma.forum.repos;

import com.roma.forum.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepo extends JpaRepository<Forum, Long> {
}
