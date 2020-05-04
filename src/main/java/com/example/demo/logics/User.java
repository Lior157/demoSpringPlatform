package com.example.demo.logics;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    String name ;
    String mail ;

    @JsonIgnore
    Object unUsefulObject; // we can ignore fields that way!

    public User(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}
