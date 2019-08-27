//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.sweater.repos;

import com.example.sweater.domain.Forum;
import com.example.sweater.domain.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
    List<Topic> findTopicsByBelongsToForum(Forum forum);

    Topic findTopicById(long id);
}