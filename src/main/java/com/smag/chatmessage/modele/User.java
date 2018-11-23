package com.smag.chatmessage.modele;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class User {
    private String idUser;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String profile;
    private Timestamp dateSignIn;

    @Id
    @Column(name = "id_user", nullable = false, length = 45)
    @NotNull
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 100)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "profile", nullable = true, length = 200)
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
        User user = (User) o;
        return Objects.equals(idUser, user.idUser) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(profile, user.profile);
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

    @Basic
    @Column(name = "date_sign_in")
    public Timestamp getDateSignIn() {
        return dateSignIn;
    }

    public void setDateSignIn(Timestamp dateSignIn) {
        this.dateSignIn = dateSignIn;
    }
}
