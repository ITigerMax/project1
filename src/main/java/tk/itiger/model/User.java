package tk.itiger.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25, message = "Name should be from 3 to 25 symbols")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 3, max = 25, message = "Surname should be from 3 to 25 symbols")
    private String surName;

    @Email
    private String email;

    public User() {
    }

    public User(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
