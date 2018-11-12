package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "chatMessage", catalog = "")
public class Message {
    private int idMessage;
    private String content;
    private Timestamp sent;
    private Timestamp received;
    private Byte read;
    private String status;

    @Id
    @Column(name = "id_message")
    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
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
        Message that = (Message) o;
        return idMessage == that.idMessage &&
                Objects.equals(content, that.content) &&
                Objects.equals(sent, that.sent) &&
                Objects.equals(received, that.received) &&
                Objects.equals(read, that.read) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, content, sent, received, read, status);
    }
}