package com.jukebox1;

import com.jukebox.*;
import com.jukebox.JukeBox;
import com.jukebox.MainMenu;
import com.jukebox.Playlist;
import com.jukebox.UserDetails;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;


public class JukeBoxImpl
{
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        com.jukebox.JukeBox.dropTable();

        com.jukebox.JukeBox.createTable();
        JukeBox.insertIntoTable();
        UserDetails.createUserDetailsTable();
        Playlist.createPlaylistTable();
        UserDetails.user();

    }
}





































































































