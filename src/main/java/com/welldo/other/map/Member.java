package com.welldo.other.map;

import java.util.Date;

class Member {
    private Integer id;
    private String name;
    private Integer age;
    private Date addDate;


    public Member(Integer id, String name, Integer age, Date addDate) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.addDate = addDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

}
