package com.example.sample_BE.dto;

public class StudentDTO {
    private String uid;
    private String name;
    private String sClass;
    private String address;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StudentDTO(String uid, String name, String sClass, String address) {
        this.uid = uid;
        this.name = name;
        this.sClass = sClass;
        this.address = address;
    }

    public StudentDTO() {
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", sClass='" + sClass + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
