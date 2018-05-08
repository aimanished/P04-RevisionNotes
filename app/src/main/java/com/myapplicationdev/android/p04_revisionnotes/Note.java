package com.myapplicationdev.android.p04_revisionnotes;

public class Note {

    private int id;
    private String description;
    private int stars;

    public Note(int id, String description, int stars) {
        this.id = id;
        this.description = description;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    //What's here?

}
