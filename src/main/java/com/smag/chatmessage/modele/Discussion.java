package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Discussion", schema = "chatMessage", catalog = "")
public class Discussion {
    private String idDiscussion;
    private Timestamp lastUpdate;
    private User userByInterlocuteur;
    private Collection<Message> messagesByIdDiscussion;

    @Id
    @Column(name = "id_discussion")
    public String getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(String idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discussion that = (Discussion) o;
        return Objects.equals(idDiscussion, that.idDiscussion) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiscussion, lastUpdate);
    }

    @ManyToOne
    @JoinColumn(name = "Interlocuteur", referencedColumnName = "id_user", nullable = false)
    public User getUserByInterlocuteur() {
        return userByInterlocuteur;
    }

    public void setUserByInterlocuteur(User userByInterlocuteur) {
        this.userByInterlocuteur = userByInterlocuteur;
    }

    @OneToMany(mappedBy = "discussionByIdDiscussion")
    public Collection<Message> getMessagesByIdDiscussion() {
        return messagesByIdDiscussion;
    }

    public void setMessagesByIdDiscussion(Collection<Message> messagesByIdDiscussion) {
        this.messagesByIdDiscussion = messagesByIdDiscussion;
    }
}
