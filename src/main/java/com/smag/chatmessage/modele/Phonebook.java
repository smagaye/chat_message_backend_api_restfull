package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Phonebook {
    private Integer idPhonebook;
    private String listPhonebook;
    private Timestamp lastUpdate;
    private User userByProprietaire;

    @Id
    @Column(name = "id_phonebook")
    public Integer getIdPhonebook() {
        return idPhonebook;
    }

    public void setIdPhonebook(Integer idPhonebook) {
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

    @ManyToOne
    @JoinColumn(name = "proprietaire", referencedColumnName = "id_user", nullable = false)
    public User getUserByProprietaire() {
        return userByProprietaire;
    }

    public void setUserByProprietaire(User userByProprietaire) {
        this.userByProprietaire = userByProprietaire;
    }

    public Set<String> splitContactsId() {
        String[] arrayOfContact =this.listPhonebook.split("__");
        Set<String> listContacts=new HashSet<>();
        if (this.listPhonebook!=null)

            for (int i=0;i<arrayOfContact.length;i++)
            {
                listContacts.add(arrayOfContact[i].trim());
            }
        return listContacts;
    }
}
