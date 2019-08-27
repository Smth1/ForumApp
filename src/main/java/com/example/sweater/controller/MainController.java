//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.sweater.controller;

import com.example.sweater.domain.Forum;
import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ForumRepo;
import com.example.sweater.repos.MessageRepo;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ForumRepo forumRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public MainController() {
    }

    @GetMapping({"/"})
    public String greeting(Model model) {
        List<Forum> forums = this.forumRepo.findAll();
        model.addAttribute("forums", forums);
        return "greeting";
    }

    @GetMapping({"/main"})
    public String main(@RequestParam(required = false,defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = this.messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = this.messageRepo.findByTag(filter);
        } else {
            messages = this.messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping({"/main"})
    public String add(@AuthenticationPrincipal User user, @Valid Message message, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErros(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
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

            model.addAttribute("message", null);
            this.messageRepo.save(message);
        }

        Iterable<Message> messages = this.messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }
}
