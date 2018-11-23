package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Phonebook {
    private int idPhonebook;
    private String listPhonebook;
    private User userByProprietaire;

    @Id
    @Column(name = "id_phonebook")
    public int getIdPhonebook() {
        return idPhonebook;
    }

    public void setIdPhonebook(int idPhonebook) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phonebook phonebook = (Phonebook) o;
        return idPhonebook == phonebook.idPhonebook &&
                Objects.equals(listPhonebook, phonebook.listPhonebook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhonebook, listPhonebook);
    }

    @ManyToOne
    @JoinColumn(name = "proprietaire", referencedColumnName = "id_user", nullable = false)
    public User getUserByProprietaire() {
        return userByProprietaire;
    }

    public void setUserByProprietaire(User userByProprietaire) {
        this.userByProprietaire = userByProprietaire;
    }

    public Set<String> splitContactsId(){
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