package com.ismailulasunal.twitterclone.models;

import jakarta.persistence.*;
@Entity
@Table(name = "roles")
public class Role {
    public Role(Integer roleID, String authority) {
        this.roleID = roleID;
        this.authority = authority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleID;
    private String authority;

    public Role() {

    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", authority='" + authority + '\'' +
                '}';
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public String getAuthority() {
        return authority;
    }
}
