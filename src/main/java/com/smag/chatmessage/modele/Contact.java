package com.smag.chatmessage.modele;

import javax.persistence.*;
import java.util.Objects;

/**********************************************
*            Classe Contact
***********************************************/
@Entity
@Table(name = "contact", schema = "chatMessage", catalog = "")
public class Contact {
    private int idContact;
    private String listContact;

    /*
     * Getter for attribut idContact
     * @return the id of the Object contact
     * */
    @Id
    @Column(name = "id_contact")
    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    /*
     * Getter for attribut listContact
     * @return the list of contact
     * */
    @Basic
    @Column(name = "list_contact")
    public String getListContact() {
        return listContact;
    }


    /*
     * Setter for attribut listContact
     * @param the new value to set for the field listContact
     * @return the list of contact
     * */
    public void setListContact(String listContact) {
        this.listContact = listContact;
    }

    /*
     * Compare two Object Contact
     * @return true if objects have the same identifiant
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact that = (Contact) o;
        return idContact == that.idContact &&
                Objects.equals(listContact, that.listContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContact, listContact);
    }
}
