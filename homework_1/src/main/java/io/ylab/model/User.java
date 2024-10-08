package io.ylab.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;


    private List<Habit> habitList;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        habitList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Iterator<Habit> getHabitList() {
        return habitList.iterator();
    }

    public void addHabit(Habit habit) {
        habitList.add(habit);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        ADMIN,
        USER
    }
}
