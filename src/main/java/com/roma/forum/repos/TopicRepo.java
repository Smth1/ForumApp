package com.roma.forum.repos;

import com.roma.forum.domain.Forum;
import com.roma.forum.domain.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
    List<Topic> findTopicsByBelongsToForum(Forum forum);

    Topic findTopicById(long id);
}
