package com.example.b07_group_19;

public class User {
    String email, password, username, role;

    public User(){

    }
    public User(String email, String password, String username, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setUsername(String username){
        this.username = username;
    }
}
