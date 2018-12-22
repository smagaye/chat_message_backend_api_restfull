package com.smag.chatmessage.modele;

public class UserRest {
    private String id;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private String profile;

    public UserRest(String id, String surname, String name, String phone, String email,String profile) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
