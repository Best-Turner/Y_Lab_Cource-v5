package io.ylab.model;

import java.time.LocalDate;

public class Habit {

    private int id;
    private int idOwner;
    private String title;
    private String description;
    private LocalDate creationDate;
    private StatusHabit status;

    public Habit() {
    }

    public Habit(int id, int idOwner, String title, String description, LocalDate creationDate, StatusHabit status) {
        this.id = id;
        this.idOwner = idOwner;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public StatusHabit getStatus() {
        return status;
    }

    public void setStatus(StatusHabit status) {
        this.status = status;
    }

    public int getIdOwner() {
        return idOwner;
    }

    @Override
    public String toString() {
        return "ID: " + id
               + "\nЗаголовок: " + title
               + "\nОписание: " + description
               + "\nСтатус: " + status
               + "\nДата создания: " + creationDate;
    }

    public enum StatusHabit {
        NEW,
        COMPLETED,
        FAILED,
        PAUSED
    }
}
