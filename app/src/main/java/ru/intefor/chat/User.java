package ru.intefor.chat;

public class User {
    private String id;
    private String name;
    private String description;
    private String image;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
