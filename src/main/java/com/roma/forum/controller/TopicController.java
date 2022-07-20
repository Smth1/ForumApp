package com.roma.forum.controller;

import com.roma.forum.domain.Message;
import com.roma.forum.domain.Topic;
import com.roma.forum.domain.User;
import com.roma.forum.repos.ForumRepo;
import com.roma.forum.repos.MessageRepo;
import com.roma.forum.repos.TopicRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/topics"})
public class TopicController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private ForumRepo forumRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public TopicController() {
    }

    @PostMapping({"{id}"})// lkj/lkj/weiu/{28973}
    public String add(@PathVariable("id") Topic topic,
                      @AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model,
                      @RequestParam("file") MultipartFile file) throws IOException {
        Message message = new Message(text, tag, user);
        message.setBelongsToTopic(topic);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(this.uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(this.uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }

        this.messageRepo.save(message);
        Iterable<Message> messages = this.messageRepo.findMessageByBelongsToTopic(topic);
        model.put("messages", messages);
        return "messageList";
    }

    @GetMapping({"{id}"})
    public String main(@RequestParam(required = false,defaultValue = "") String filter,
                       @PathVariable("id") Topic topic, Model model) {
        Iterable<Message> messages = this.messageRepo.findMessageByBelongsToTopic(topic);
        model.addAttribute("messages", messages);
        model.addAttribute("topic", topic);
        return "messageList";
    }
}
