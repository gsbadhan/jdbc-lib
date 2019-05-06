package com.jdbclib.application.pojo.model;

public class Emp {
    private Long   id;
    private String name;
    private String dept;
    private float  salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + "]";
    }

}
