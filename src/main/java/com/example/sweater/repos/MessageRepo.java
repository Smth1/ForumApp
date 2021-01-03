package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.Topic;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);

    List<Message> findMessageByBelongsToTopic(Topic topic);
}
