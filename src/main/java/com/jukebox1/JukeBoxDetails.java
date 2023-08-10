package com.jukebox1;

public class JukeBoxDetails
{
    int song_Id;
    String albums_Artist;
    String genre;
    String name;

    public JukeBoxDetails(int song_Id, String albums_Artist, String genre, String name) {
        this.song_Id = song_Id;
        this.albums_Artist = albums_Artist;
        this.genre = genre;
        this.name = name;
    }

    public  int getSong_Id() {
        return song_Id;
    }

    public void setSong_Id(int song_Id) {
        this.song_Id= song_Id;
    }

    public String getAlbums_Artist() {
        return albums_Artist;
    }

    public void setAlbums_Artist(String albums_Artist) {
        this.albums_Artist = albums_Artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JukeBoxDetails{" +
                "song_Id=" + song_Id +
                ", albums_Artist='" + albums_Artist + '\'' +
                ", genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
