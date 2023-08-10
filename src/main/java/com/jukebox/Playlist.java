package com.jukebox;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Playlist
{
  public static void createPlaylistTable() throws SQLException {
     Statement st=JukeBox.connection();
     st.executeUpdate("create table if not exists Playlist(playlist_Id int auto_increment primary key, playlist_name varchar(30),song_Id int , song_path varchar(200))");
  }
  public  static void insertIntoPlaylistTable(String playlist_name, int song_Id,String song_path) throws SQLException
  {
      Statement st=JukeBox.connection();
      st.executeUpdate("insert into Playlist(playlist_name, song_Id,song_path) values('"+playlist_name+"',"+song_Id+", '"+song_path+"')");
  }

  public static void displayPlaylistTable() throws SQLException, NullPointerException {
      Statement st=JukeBox.connection();

      System.out.println("**************************  Your Playlists  **************************  ");
      ResultSet rs= st.executeQuery("select * from Playlist");
      System.out.println("**************************************************************************************************");
      System.out.println("Playlist Id    Playlist Name           Song Id            Song Path");
      System.out.println("**************************************************************************************************");
      while (rs.next()) {

          System.out.format("%-10d %-20s %-20d %-20s", rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
          System.out.println();
      }
  }
  public static void createPlaylist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
      System.out.println("Give your playlist a name.");
      Boolean flag=true;
      Scanner sc = new Scanner(System.in);
      String playlistName = sc.next();
      while (true) {
          System.out.println("Enter the Song Id");
          int song_Id = sc.nextInt();
          switch (song_Id) {
              case 4: {
                  insertIntoPlaylistTable(playlistName, song_Id, "D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Pasoori.wav");
                  System.out.println("Song Added Successfully");
                  System.out.println("**************************************************************************************************");
                  break;
              }

              case 15: {
                  insertIntoPlaylistTable(playlistName, song_Id, "D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Aashayein.wav");
                  System.out.println("Song Added Successfully");
                  System.out.println("**************************************************************************************************");
                  break;
              }
              case 24: {
                  insertIntoPlaylistTable(playlistName, song_Id,"D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Desire.wav" );
                  System.out.println("Song Added Successfully");
                  System.out.println("**************************************************************************************************");
                  break;
              }
              case 30:
              {
                  insertIntoPlaylistTable(playlistName, song_Id,"D:\\\\Intellij Projects\\\\JukeBox\\\\src\\\\main\\\\resources\\\\Cheap Thrills.wav" );
                  System.out.println("Song Added Successfully");
                  System.out.println("**************************************************************************************************");
                  break;
              }
              default:
              {
                  System.out.println("Invalid Choice");
                  MainMenu.library();
              }

          }
          System.out.println("1. Add more songs to the playlist");
          System.out.println("2. Go to library");
          System.out.println("Enter the choice");
          int a= sc.nextInt();
          System.out.println("**************************************************************************************************");
          if(a==2)
          {
              flag=false;
              MainMenu.library();
              break;
          }

      }
  }
}
