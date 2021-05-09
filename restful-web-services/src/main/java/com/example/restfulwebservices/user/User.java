package com.example.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user.")
@Entity
public class User {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Size(min = 2, message ="name should have at least 2 characters")
    @ApiModelProperty(notes = "name should have at least 2 characters")
    private String name;
    
    @Past()
    @ApiModelProperty(notes = "Birth date should be in the past")
    private Date birtDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    
    protected User() {}

    public User(Integer id, String name, Date birtDate) {
        this.id = id;
        this.name = name;
        this.birtDate = birtDate;
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

    public Date getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User [birtDate=" + birtDate + ", id=" + id + ", name=" + name + "]";
    }
    
}
