package com.smag.chatmessage.modele;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Phonebook {
    private Long idPhonebook;
    private String listPhonebook;
    private Timestamp lastUpdate;
    private String owner;

    @Id
    @Column(name = "id_phonebook")
    public Long getIdPhonebook() {
        return idPhonebook;
    }

    public void setIdPhonebook(Long idPhonebook) {
        this.idPhonebook = idPhonebook;
    }

    @Basic
    @Column(name = "list_phonebook")
    public String getListPhonebook() {
        return listPhonebook;
    }

    public void setListPhonebook(String listPhonebook) {
        this.listPhonebook = listPhonebook;
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
        Phonebook phonebook = (Phonebook) o;
        return Objects.equals(idPhonebook, phonebook.idPhonebook) &&
                Objects.equals(listPhonebook, phonebook.listPhonebook) &&
                Objects.equals(lastUpdate, phonebook.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhonebook, listPhonebook, lastUpdate);
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> splitContactsId() {
        String[] arrayOfContact =this.listPhonebook.split("__");
        List<String> listContacts=new ArrayList<>();
        if (this.listPhonebook!=null)

            for (int i=0;i<arrayOfContact.length;i++)
            {
                listContacts.add(arrayOfContact[i].trim());
            }
        return listContacts;
    }
}
