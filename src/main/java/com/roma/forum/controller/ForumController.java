package com.roma.forum.controller;

import com.roma.forum.domain.Forum;
import com.roma.forum.domain.Topic;
import com.roma.forum.repos.ForumRepo;
import com.roma.forum.repos.MessageRepo;
import com.roma.forum.repos.TopicRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forums")
public class ForumController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private ForumRepo forumRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public ForumController() {
    }

    @GetMapping({"{id}"})
    public String getOne(@PathVariable("id") Forum forum, Model model) {
        List<Topic> topics = this.topicRepo.findTopicsByBelongsToForum(forum);
        model.addAttribute("topics", topics);
        return "topics";
    }
}
