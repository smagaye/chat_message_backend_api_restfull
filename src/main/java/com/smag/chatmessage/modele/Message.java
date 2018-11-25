package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message {
    private Integer idMessage;
    private String content;
    private String media;
    private Timestamp sent;
    private Timestamp received;
    private Byte read;
    private String status;
    private Discussion discussionByIdDiscussion;

    @Id
    @Column(name = "id_message")
    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "media")
    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Basic
    @Column(name = "sent")
    public Timestamp getSent() {
        return sent;
    }

    public void setSent(Timestamp sent) {
        this.sent = sent;
    }

    @Basic
    @Column(name = "received")
    public Timestamp getReceived() {
        return received;
    }

    public void setReceived(Timestamp received) {
        this.received = received;
    }

    @Basic
    @Column(name = "read")
    public Byte getRead() {
        return read;
    }

    public void setRead(Byte read) {
        this.read = read;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(idMessage, message.idMessage) &&
                Objects.equals(content, message.content) &&
                Objects.equals(media, message.media) &&
                Objects.equals(sent, message.sent) &&
                Objects.equals(received, message.received) &&
                Objects.equals(read, message.read) &&
                Objects.equals(status, message.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, content, media, sent, received, read, status);
    }

    @ManyToOne
    @JoinColumn(name = "id_discussion", referencedColumnName = "id_discussion", nullable = false)
    public Discussion getDiscussionByIdDiscussion() {
        return discussionByIdDiscussion;
    }

    public void setDiscussionByIdDiscussion(Discussion discussionByIdDiscussion) {
        this.discussionByIdDiscussion = discussionByIdDiscussion;
    }
}
