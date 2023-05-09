package models.implementation;

import models.Model;


public class Album extends Model {
   private  int releaseYear;
    private String title;
    private String artist;
    public Album(int id, int releaseYear, String title, String artist) {
        super(id, null);
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
    }
    public Album(int id)
    {
        super(id,null);
    }

    public Album(int id,String name) {
        super(id,name);
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                '}';
    }
}
