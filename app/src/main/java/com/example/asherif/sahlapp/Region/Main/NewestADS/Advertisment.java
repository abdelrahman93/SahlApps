package com.example.asherif.sahlapp.Region.Main.NewestADS;

public class Advertisment {
    private String title, genre, year;
    private int thumbnail;

    public Advertisment(){

    }
    public Advertisment(String title, String genre, String year){
        this.title = title;
        this.genre = genre;
        this.year = year;
     //   this.thumbnail = thumbnail;

    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
