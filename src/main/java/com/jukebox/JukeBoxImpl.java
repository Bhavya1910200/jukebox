package com.jukebox;

import com.sun.security.jgss.GSSUtil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


public class JukeBoxImpl
{
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Song.dropTable();
        Song.createSongTable();
        Song.insertIntoSongTable();
        JukeBox.dropTable();
        JukeBox.createTable();
        JukeBox.insertIntoTable();
        UserDetails.createUserDetailsTable();
        Playlist.createPlaylistTable();
        UserDetails.user();

    }
}





































































































