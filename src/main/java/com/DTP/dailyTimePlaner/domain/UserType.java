//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.12.04 at 05:22:44 PM EET 
//


package com.DTP.dailyTimePlaner.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import com.DTP.dailyTimePlaner.domain.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@javax.persistence.Entity(name = "user_test")
public class UserType
    implements UserDetails
{

    @Id
    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false,unique = true)
    protected String email;

    @Column(name = "password", columnDefinition = "varchar(100)", nullable = false)
    @XmlElement(required = true)
    protected String password;


    @Column(name = "surname", columnDefinition = "varchar(45)")
    @XmlElement(required = true)
    protected String surname;

    @Column(name = "name", columnDefinition = "varchar(45)")
    @XmlElement(required = true)
    protected String name;

    @Column(name = "login", columnDefinition = "varchar(100)")
    @XmlElement(required = true)
    protected String login;

    @Column(name = "gender", columnDefinition = "varchar(10)")
    protected String gender;

    @Column(name = "active", columnDefinition = "BIT(1)", nullable = false)
    protected boolean active;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id", nullable = false)
    protected RoleType role;

    public UserType() {
        this.active = true;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }


    public void setName(String value) {
        this.name = value;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String value) {
        this.surname = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String value) {
        this.login = value;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}