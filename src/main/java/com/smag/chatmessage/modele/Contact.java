package com.smag.chatmessage.modele;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Contact {
    private int idContact;
    private String listContact;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "id_contact")
    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    @Basic
    @Column(name = "list_contact")
    public String getListContact() {
        return listContact;
    }

    public void setListContact(String listContact) {
        this.listContact = listContact;
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
        Contact contact = (Contact) o;
        return idContact == contact.idContact &&
                Objects.equals(listContact, contact.listContact) &&
                Objects.equals(lastUpdate, contact.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContact, listContact, lastUpdate);
    }
}
