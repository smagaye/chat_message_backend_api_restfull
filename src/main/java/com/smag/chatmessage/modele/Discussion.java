package com.smag.chatmessage.modele;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Discussion {
    private String idDiscussion;
    private Timestamp lastUpdate;
    private String transmetter;
    private String receiver;

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

    @Basic
    @Column(name = "transmetter")
    public String getTransmetter() {
        return transmetter;
    }

    public void setTransmetter(String transmetter) {
        this.transmetter = transmetter;
    }

    @Basic
    @Column(name = "receiver")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
