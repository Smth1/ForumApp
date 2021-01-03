package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Message {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @NotBlank(
            message = "Please fill the message"
    )
    @Length(
            max = 2048,
            message = "Message too long (more than 2kb)"
    )
    private String text;

    @Length(
            max = 255,
            message = "Tag too long (more than 255)"
    )
    private String tag;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id"
    )
    private User author;

    @ManyToOne
    @JoinColumn(
            name = "topic_id"
    )
    private Topic belongsToTopic;

    private String filename;

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Message() {
    }

    public Message(String text, String tag, User author, String filename) {
        this.text = text;
        this.tag = tag;
        this.author = author;
        this.filename = filename;
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getAuthorName() {
        return this.author != null ? this.author.getUsername() : "<net avtora>";
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Topic getBelongsToTopic() {
        return this.belongsToTopic;
    }

    public void setBelongsToTopic(Topic belongsToTopic) {
        this.belongsToTopic = belongsToTopic;
    }
}
