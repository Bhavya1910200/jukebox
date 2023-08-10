package com.jukebox1;
import com.jukebox.MainMenu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AudioImp
{
    public static void playMusic(String filepath) throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        Scanner sc=new Scanner(System.in);
        File file = new File(filepath);
        Clip clip = AudioSystem.getClip();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip.open(audioStream);
        int input;
        int flag = 0;
        long clippos = 0;

        while (flag == 0)
        {
            System.out.println("\n**************************** Enter Your Choice  ****************************\n----- ---- ------\n 1. Play\n 2. Pause\n 3. Resume\n 4. Restart\n 5. Forward\n 6.Backwards\n 0.Exit");

            input = sc.nextInt();
            switch (input) {
                case 1:
                    clip.start();
                    System.out.println("+------------+");
                    System.out.println("|Playing Song|");
                    System.out.println("+------------+");
                    break;
                case 2:
                    clippos = clip.getMicrosecondPosition();
                    clip.stop();
                    System.out.println("+-----------+");
                    System.out.println("|Song Paused|");
                    System.out.println("+-----------+");
                    break;
                case 3:
                    clip.setMicrosecondPosition(clippos);
                    clip.start();
                    System.out.println("+------------+");
                    System.out.println("|Song Resumed|");
                    System.out.println("+------------+");
                    break;
                case 4:
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    System.out.println("+--------------+");
                    System.out.println("|Song Restarted|");
                    System.out.println("+--------------+");
                    break;
                case 5:
                    System.out.println("+-----------------+");
                    System.out.println("|Forwarding by 50s|");
                    System.out.println("+-----------------+");
                    clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 5000000);
                    break;
                case 6:
                    System.out.println("+-----------------+");
                    System.out.println("|Backward by 50s|");
                    System.out.println("+-----------------+");
                    clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 5000000);
                    break;

                case 0:
                    clip.close();
                    flag = 1;

                    break;
                default:
                    System.out.println("Not a valid Input");
            }
        }
    }


}

