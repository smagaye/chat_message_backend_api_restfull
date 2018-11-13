package com.smag.chatmessage.modele;


import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "chatMessage", catalog = "")
public class User {
    private String idUser;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String profile;
    private Collection<Discussion> discussionsByIdUser;
    private Collection<Contact> contactsByIdUser;

    @Id
    @Column(name = "id_user")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "profile")
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(profile, that.profile);
    }

    @Override
    public String toString() {
        return super.toString() +name+phone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, surname, name, phone, email, password, profile);
    }

    public void formatToUpdate(User userPost){
        if(userPost!=null){

            if(userPost.getEmail()!=null) this.setEmail(userPost.getEmail());
            if(userPost.getIdUser()!=null) this.setIdUser(userPost.getIdUser());
            if(userPost.getName()!=null) this.setName(userPost.getName());
            if(userPost.getPhone()!=null) this.setPhone(userPost.getEmail());
            if(userPost.getPassword()!=null) this.setPassword(userPost.getPassword());
            if(userPost.getProfile()!=null) this.setProfile(userPost.getProfile());
        }
    }

    @OneToMany(mappedBy = "userByInterlocuteur")
    public Collection<Discussion> getDiscussionsByIdUser() {
        return discussionsByIdUser;
    }

    public void setDiscussionsByIdUser(Collection<Discussion> discussionsByIdUser) {
        this.discussionsByIdUser = discussionsByIdUser;
    }

    @OneToMany(mappedBy = "userByProprietaire")
    public Collection<Contact> getContactsByIdUser() {
        return contactsByIdUser;
    }

    public void setContactsByIdUser(Collection<Contact> contactsByIdUser) {
        this.contactsByIdUser = contactsByIdUser;
    }
}
