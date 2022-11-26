package com.simplilearn.sportyShoe;

import java.util.UUID;

public class Login {

    private String userName;
    private String password;
    private String id;

    public Login() {

        this.id = UUID.randomUUID().toString();
    }



    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}
