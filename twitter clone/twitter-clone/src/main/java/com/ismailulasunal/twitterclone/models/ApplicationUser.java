package com.ismailulasunal.twitterclone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) //TODO FETCH TYPE A BAK
    @JoinTable( // TODO JOINTABLE A BAK NEDEN OLUYOR DIYE
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name= "role_id")}
    )
    private Set<Role> authorities;

    private Boolean enabled;
    @Column(nullable = true)
    @JsonIgnore
    private Long verification;

    public ApplicationUser() {
        this.authorities = new HashSet<>();
        this.enabled = false;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", enabled=" + enabled +
                ", verification=" + verification +
                '}';
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getVerification() {
        return verification;
    }

    public void setVerification(Long verification) {
        this.verification = verification;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }


}
