package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message {
    private Long idMessage;
    private String content;
    private String media;
    private String idDiscussion;
    private String creator;
    private Timestamp timeSent;
    private Timestamp timeReceived;
    private Integer readStatus;
    private Integer deleteStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_message")
    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
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
    @Column(name = "id_discussion")
    public String getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(String idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", content='" + content + '\'' +
                ", media='" + media + '\'' +
                ", idDiscussion='" + idDiscussion + '\'' +
                ", creator='" + creator + '\'' +
                ", timeSent=" + timeSent +
                ", timeReceived=" + timeReceived +
                ", readStatus=" + readStatus +
                ", deleteStatus=" + deleteStatus +
                '}';
    }

    @Basic
    @Column(name = "time_sent")
    public Timestamp getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Timestamp timeSent) {
        this.timeSent = timeSent;
    }

    @Basic
    @Column(name = "time_received")
    public Timestamp getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(Timestamp timeReceived) {
        this.timeReceived = timeReceived;
    }

    @Basic
    @Column(name = "read_status")
    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    @Basic
    @Column(name = "delete_status")
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
