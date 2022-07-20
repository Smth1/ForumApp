package com.roma.forum.repos;

import com.roma.forum.domain.Message;
import com.roma.forum.domain.Topic;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);

    List<Message> findMessageByBelongsToTopic(Topic topic);
}
