package com.roma.forum.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(
            name = "name"
    )

    private String name;
    @Column(
            name = "text"
    )
    private String text;

    @ManyToOne
    @JoinColumn(
            name = "forum_id"
    )
    Forum belongsToForum;

    @OneToMany(
            mappedBy = "belongsToTopic"
    )
    List<Message> messages;

    public Topic() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Forum getBelongsToForum() {
        return this.belongsToForum;
    }

    public void setBelongsToForum(Forum belongsToForum) {
        this.belongsToForum = belongsToForum;
    }
}
