package com.example.androidhealthcareproject.user_manager.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="orderplace")
public class Orderplace {
    @Id
    private int id;
    private int user_id ;
    private String fullname;
    private String address;
    private String contact;
    private int age ;
    private String date;
    private String time;
    private Float amount;
    private String otype;

    public Orderplace() {
    }
    @Transient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public Orderplace(int id, int user_id, String fullname, String address, String contact, int age, String date, String time, Float amount, String otype) {
        this.id = id;
        this.user_id = user_id;
        this.fullname = fullname;
        this.address = address;
        this.contact = contact;
        this.age = age;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.otype = otype;
    }
}
