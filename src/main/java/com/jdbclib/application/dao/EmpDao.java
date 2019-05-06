package com.jdbclib.application.dao;

import java.util.List;

import com.jdbclib.application.pojo.model.Emp;

public interface EmpDao {
    boolean save(String name, String dept, float salary);
    List<Emp> getAll();
}
