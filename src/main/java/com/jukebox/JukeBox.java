package com.jukebox;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JukeBox {
    public static Statement connection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "root@123");
        Statement st = con.createStatement();
        return st;
    }

    public static void createTable() throws SQLException {
        Statement st = JukeBox.connection();
        st.execute("create table JukeBox(song_Id int, Albums_Artist varchar(30), Genre varchar(20),Name varchar(20), constraint primary key(song_Id))");
    }

    public static void insertIntoTable() throws SQLException {
        Statement st = JukeBox.connection();
        st.executeUpdate("insert into JukeBox values(4,'AliSethi', 'Pop', 'Pasoori')");
        st.executeUpdate("insert into JukeBox values(15,'KK','IndiaFilmPop', 'Aashayein' )");
        st.executeUpdate("insert into JukeBox values(24,'APDillon','Pop','Desire')");
        st.executeUpdate("insert into JukeBox values(30,'Sia','Pop','Cheap Thrills')");
    }

    public static void displayTable() throws SQLException {
        System.out.println("**************************************************************************************************");
        System.out.println("   Display All Songs  ");
        System.out.println("**************************************************************************************************");
        ResultSet rs;
        Statement st = JukeBox.connection();
        rs = st.executeQuery("select *from JukeBox");
        System.out.println("**************************************************************************************************");
        System.out.println("Song Id       Album's Artist           Genre           Name");
        System.out.println("**************************************************************************************************");
        while (rs.next()) {

            System.out.format("%-10d %-20s %-20s %-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            System.out.println();
        }
    }

    public static List<JukeBoxDetails> getDetailList() throws SQLException {

        List<JukeBoxDetails> li = new ArrayList<>();
        Statement st = JukeBox.connection();
        ResultSet rs = st.executeQuery("select * from JukeBox");
        while (rs.next()) {
            JukeBoxDetails details = new JukeBoxDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            li.add(details);
        }
        return li;
    }

    public static void dropTable() throws SQLException {
        Statement st = JukeBox.connection();
        st.executeUpdate("drop table JukeBox");
    }


    public static void searchByName() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("Choose Artist Name : ");
        System.out.println("**************************************************************************************************");
        System.out.println("1. AliSethi");
        System.out.println("2. KK");
        System.out.println("3. APDillon");
        System.out.println("**************************************************************************************************");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the artist");
        String name = sc.next();
        System.out.println("**************************************************************************************************");
        Iterator<JukeBoxDetails> detailsIterator = getDetailList().iterator();

        while (detailsIterator.hasNext()) {
            JukeBoxDetails i = detailsIterator.next();
            if (Objects.equals(name, i.getAlbums_Artist()))
            {
                System.out.println("Details Of the Song : ");
                System.out.println("Song Id          Artist            Genre            Song Name");
                System.out.println(i.getSong_Id() + "                  " + i.getAlbums_Artist() + "                 " + i.getGenre() + "                 " + i.getName());
                break;
            }
        }
        System.out.println("**************************************************************************************************");
        System.out.println("Press 1 to listen to the music");
        System.out.println("Press 0 to go back to Library");
        int choice= sc.nextInt();
        System.out.println("**************************************************************************************************");
        switch (choice)
        {
            case 1:
                Song.listenToMusic();
                break;
            case 0:
                MainMenu.library();
                break;
        }


    }

    public static void searchBySong() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {

        System.out.println("1. Pasoori");
        System.out.println("2. Aashayein");
        System.out.println("3. Desire");
        System.out.println("4. Go back to Home");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the Song");
        String name = sc.next();

        Iterator<JukeBoxDetails> detailsIterator = getDetailList().iterator();
        while (detailsIterator.hasNext()) {
            JukeBoxDetails i = detailsIterator.next();
            if (Objects.equals(name, i.getName()))
            {
                System.out.println("Details of the song");
                System.out.println("Song Id       Artist        Genre            Song Name");
                System.out.println(i.getSong_Id() + " " + i.getAlbums_Artist() + " " + i.getGenre() + " " + i.getName());
                break;
            }

        }
        System.out.println("**************************************************************************************************");
        System.out.println("Press 1 to listen to the music");
        System.out.println("Press 0 to go back to Library");
        int choice= sc.nextInt();
        System.out.println("**************************************************************************************************");
        switch (choice)
        {
            case 1:
                Song.listenToMusic();
                break;
            case 0:
                MainMenu.library();
                break;
        }

    }


    public static void searchByGenre() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("1. Rock");
        System.out.println("2. Pop Music");
        System.out.println("3. Hip hop Music");
        System.out.println("4. Go back to Home");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the Song");
        String name = sc.next();
        System.out.println("Details of the song");
        Iterator<JukeBoxDetails> detailsIterator = getDetailList().iterator();
        while (detailsIterator.hasNext()) {
            JukeBoxDetails i = detailsIterator.next();
            if (Objects.equals(name, i.getGenre()))
            {
                System.out.println("Details of the song");
                System.out.println("Song Id        Artist          Genre          Song Name");
                System.out.println(i.getSong_Id() + " " + i.getAlbums_Artist() + " " + i.getGenre() + " " + i.getName());
                break;
            }
        }
        System.out.println("**************************************************************************************************");
        System.out.println("Press 1 to listen to the music");
        System.out.println("Press 0 to go back to Library");
        int choice= sc.nextInt();
        System.out.println("**************************************************************************************************");
        switch (choice)
        {
            case 1:
                Song.listenToMusic();
                break;
            case 0:
                MainMenu.library();
                break;
        }
    }
}



