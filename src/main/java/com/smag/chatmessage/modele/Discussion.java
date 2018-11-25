package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Discussion {
    private String idDiscussion;
    private Timestamp lastUpdate;
    private User userByEmetteur;
    private User userByRecepteur;
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
    @JoinColumn(name = "emetteur", referencedColumnName = "id_user", nullable = false)
    public User getUserByEmetteur() {
        return userByEmetteur;
    }

    public void setUserByEmetteur(User userByEmetteur) {
        this.userByEmetteur = userByEmetteur;
    }

    @ManyToOne
    @JoinColumn(name = "recepteur", referencedColumnName = "id_user", nullable = false)
    public User getUserByRecepteur() {
        return userByRecepteur;
    }

    public void setUserByRecepteur(User userByRecepteur) {
        this.userByRecepteur = userByRecepteur;
    }

    @OneToMany(mappedBy = "discussionByIdDiscussion")
    public Collection<Message> getMessagesByIdDiscussion() {
        return messagesByIdDiscussion;
    }

    public void setMessagesByIdDiscussion(Collection<Message> messagesByIdDiscussion) {
        this.messagesByIdDiscussion = messagesByIdDiscussion;
    }
}
