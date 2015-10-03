package net.kiberion.jpa.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")

public class Employee {

    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private Integer age;

    
    public Employee() {
    }
    
    public Employee(Integer id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Employee(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
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

    
}
