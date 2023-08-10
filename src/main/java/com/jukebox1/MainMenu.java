package com.jukebox1;

import com.jukebox.AudioImp;
import com.jukebox.JukeBox;
import com.jukebox.Playlist;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainMenu {
    public static void home() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        com.jukebox.JukeBox.displayTable();
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        while (flag == 0) {
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("Enter the Choice");
            System.out.println("1. Search");
            System.out.println("2. Library");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            System.out.println("+----------------------------------------------------------------------------+");
            switch (choice) {
                case 1: {
                    MainMenu.search();
                    break;
                }
                case 2: {
                   MainMenu.library();
                    break;
                }
                case 3:
                {
                    flag=1;
                    break;
                }
                default: {
                    System.out.println("Invalid Input");
                }
            }
        }
    }
    public static void search() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("Enter your choice: ");
        System.out.println("Search by");
        System.out.println("1. Artist");
        System.out.println("2. Genre");
        System.out.println("3. Song");
        System.out.println("0. Go back to home");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("+---------------------------------------------------------------------------------+");
        switch (choice) {
            case 1: {
                com.jukebox.JukeBox.searchByName();
                break;
            }
            case 2: {
                com.jukebox.JukeBox.searchByGenre();
                break;
            }
            case 3: {
                com.jukebox.JukeBox.searchBySong();
                break;
            }
            case 0:{
                MainMenu.home();
                break;
            }

            default:{
                System.out.println("Invalid Choice");
                search();
            }
            break;
        }
    }

    public static void library() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException
    {

                System.out.println("What you want to do???");
                System.out.println("1. Create playlist");
                System.out.println("2. Yours playlist");
                System.out.println("0. Go back to home");
                Scanner sc = new Scanner(System.in);
                int a = sc.nextInt();
                switch (a) {
                    case 1: {
                        Playlist.createPlaylist();
                        break;
                    }
                    case 2: {
                        Playlist.displayPlaylistTable();
                        System.out.println("Enter your choice");
                        System.out.println("Do you want to play the music?? (1. Yes)");
                        System.out.println("0. Go back to Library");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1: {
                                System.out.println("+---------------------------------------------------------------------------------+");
                                System.out.println("Enter Song Id");
                                int Song_Id = sc.nextInt();
                                System.out.println("+---------------------------------------------------------------------------------+");
                                Statement statement= JukeBox.connection();
                                ResultSet rs=null;
                                rs= statement.executeQuery("select song_path from Playlist where song_Id="+Song_Id+"");
                                if(rs.next())
                                {
                                    AudioImp.playMusic(rs.getNString(1));
                                    break;
                                }
                                else {
                                    System.out.println("Song is not available in playlist please add the song first");
                                    System.out.println();
                                }

                            }
                                case 0: {
                                    MainMenu.library();
                                    break;
                                }
                            }
                            break;
                    }
                    case 0: {
                        home();
                        break;
                    }
                }
    }

}
