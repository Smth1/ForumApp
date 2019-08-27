//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.Topic;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);

    List<Message> findMessageByBelongsToTopic(Topic topic);
}