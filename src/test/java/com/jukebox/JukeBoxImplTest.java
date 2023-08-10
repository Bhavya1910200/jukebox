package com.jukebox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JukeBoxImplTest
{

    JukeBoxImpl jukeBox;


    @BeforeEach
    void setUp()
    {
        jukeBox = new JukeBoxImpl();
//
    }

    @AfterEach
    void tearDown()
    {
       jukeBox  =null;

    }


    @Test
    void getDetailsListTest() throws SQLException
    {
        List<JukeBoxDetails> list = JukeBox.getDetailList();
        Iterator<JukeBoxDetails> i=list.iterator();
        JukeBoxDetails i1=i.next();
        assertEquals(4,i1.getSong_Id());
    }

}