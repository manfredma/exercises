package com.manfred.rdbms.dynamic_sql;

public class User {
    private Long id;
    private String name;
    private Integer age;
    
    // 构造器
    public User() {}
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    
    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}