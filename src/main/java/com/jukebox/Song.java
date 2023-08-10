package com.jukebox;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Song
{
    int Song_Id;
    String Song_path;
    String genre;
    String artist;
    String song_name;

    public Song(int song_Id, String song_path, String genre, String artist, String song_name)
    {
        Song_Id = song_Id;
        Song_path = song_path;
        this.genre = genre;
        this.artist = artist;
        this.song_name = song_name;
    }

    public int getSong_Id() {
        return Song_Id;
    }

    public void setSong_Id(int song_Id) {
        Song_Id = song_Id;
    }



    public static void createSongTable() throws SQLException
    {
        Statement st= JukeBox.connection();
        st.execute("create table song(song_Id int,genre varchar(20), song_path varchar(200),song_name varchar(20),song_artist varchar(20), constraint primary key(song_Id))");
    }
    public static void insertIntoSongTable() throws SQLException {
        Statement st=JukeBox.connection();
        st.executeUpdate("Insert into song values(4,'Pop Music','D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Pasoori.wav','Pasoori','Ali Sethi')");
        st.executeUpdate("Insert into song values(15,'India flim Pop','D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Aashayein.wav', 'Aashayein','KK')");
        st.executeUpdate("Insert into song values(24,'Pop Music','D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Desire.wav', 'Desire','AP Dillon')");
        st.executeUpdate("Insert into song values(30,'Pop Music','D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Cheap Thrills.wav', 'Cheap Thrills','Sia')");
    }
    public static void displaySongTable() throws SQLException {
        Statement statement=JukeBox.connection();
        ResultSet rs=statement.executeQuery("select * from Song");
        while (rs.next())
        {
            System.out.format("%-10d %-20s %-20s %-20s", rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5) );
            System.out.println();
        }
    }
    public List<Song> getSongList() throws SQLException
    {
        List<Song> list=new ArrayList<>();
        Statement statement= JukeBox.connection();
        ResultSet rs=statement.executeQuery("select * from song");
        while(rs.next())
        {
            Song song=new Song(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5));
            list.add(song);
        }
        return list;
    }

    public static void dropTable() throws SQLException {
        Statement statement=JukeBox.connection();
        statement.execute("drop table song");
    }

   public static void listenToMusic() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
       Statement statement=JukeBox.connection();
       Scanner scanner=new Scanner(System.in);
       System.out.println("Enter the song Id");
       int id=scanner.nextInt();
       ResultSet rs=statement.executeQuery("select song_path from song where song_Id='"+id+"'");
       while (rs.next())
       {
           AudioImp.playMusic(rs.getString(1));
           break;
       }
    }
}
