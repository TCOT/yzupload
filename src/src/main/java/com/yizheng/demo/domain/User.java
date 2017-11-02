package com.yizheng.demo.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @Column(columnDefinition = "varchar(64) binary")
    private String id;

    private String username;

    @Column(columnDefinition = "varchar(64) binary")
    private String avataraddr;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvataraddr() {
        return avataraddr;
    }

    public void setAvataraddr(String avataraddr) {
        this.avataraddr = avataraddr;
    }
}
