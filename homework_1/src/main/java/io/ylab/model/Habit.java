package io.ylab.model;

import java.util.Date;

public class Habit {

    private int id;
    private int idOwner;
    private String title;
    private String description;
    private Date creationDate;
    private StatusHabit status;

    public Habit() {
    }

    public Habit(int id, int idOwner, String title, String description, Date creationDate, StatusHabit status) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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

    public enum StatusHabit {
        NEW,
        COMPLETED,
        FAILED,
        PAUSED
    }
}
