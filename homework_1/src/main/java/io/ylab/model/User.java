package io.ylab.model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User() {
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "\n-----------------------------\nID: " + id
               + "\nИмя пользователя: " + name
               + "\nЛогин: " + email
               + "\nПароль: " + password
               + "\nСтатус: " + (role.name().equals(Role.USER.toString()) ? "Пользователь" : "Администратор")
                +"\n-----------------------------\n";
    }

    public enum Role {
        ADMIN,
        USER
    }
}
