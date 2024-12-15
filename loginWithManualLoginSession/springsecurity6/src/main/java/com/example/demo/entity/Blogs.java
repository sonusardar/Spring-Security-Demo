package com.example.demo.entity;

import jakarta.persistence.*;

@Entity

public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    @ManyToOne
    private UserDtls auther;

    public Blogs() {
    }

    public Blogs(Integer id, String title, String description, UserDtls auther) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.auther = auther;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDtls getAuther() {
        return auther;
    }

    public void setAuther(UserDtls auther) {
        this.auther = auther;
    }

    @Override
    public String toString() {
        return "Blogs{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", auther=" + auther +
                '}';
    }
}
