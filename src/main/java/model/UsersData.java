package model;

import javax.jws.soap.SOAPBinding;

public class UsersData {
    private String loginField;

    private String nameField;

    private String surnameField;

    private String fathernameField;

    private String phoneField;

    private String emailField;

    private String passwordField;

    private String groupField;

    private String descriptionField;

    public UsersData withLogin (String loginField){
        this.loginField = loginField;
        return this;
    }

    public UsersData withName(String nameField){
        this.nameField = nameField;
        return this;
    }

    public UsersData withSurname (String surnameField){
        this.surnameField = surnameField;
        return this;
    }

    public UsersData withFathername(String fathernameField){
        this.fathernameField = fathernameField;
        return this;
    }

    public UsersData withPhone (String phoneField){
        this.phoneField = phoneField;
        return this;
    }


    public UsersData withEmail(String emailField){
        this.emailField = emailField;
        return this;
    }

    public UsersData withPassword(String passwordField){
        this.passwordField = passwordField;
        return this;
    }


    public UsersData withGroup (String groupField){
        this.groupField = groupField;
        return this;
    }

    public UsersData withDescription (String descriptionField){
        this.descriptionField = descriptionField;
        return this;
    }

    public String getLogin() { return loginField; }
    public String getName(){
        return nameField;
    }
    public String getSurname() { return surnameField; }
    public String getFathername(){
        return fathernameField;
    }
    public String getPhone(){
        return phoneField;
    }
    public String getEmail() { return emailField; }
    public String getPassword() { return passwordField; }
    public String getGroup() { return groupField; }
    public String getDescription(){
        return descriptionField;
    }
}
