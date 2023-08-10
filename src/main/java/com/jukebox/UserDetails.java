package com.jukebox;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserDetails {
    public static void user() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("**********************************");
        System.out.println("*********   JUKEBOX   ************");
        System.out.println("**********************************");
        System.out.println(" Enter the Credentials");
        System.out.println("**********************************");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. New User/ SignUp");
        System.out.println("2. SignIn");
        int a = sc.nextInt();
        switch (a) {
            case 1: {
                System.out.println("**************************************************************************************************");
                System.out.println("Enter your name :");
                String name = sc.next();
                System.out.println("**************************************************************************************************");
                System.out.println("Enter EmailId");
                String emailId = sc.next();
                System.out.println("**************************************************************************************************");
                System.out.println("Create Password (it should contains at least 8 characters)");
                String password = null;
                while (true) {
                        password = sc.next();
                        if (password.length() >= 8)
                        {
                            System.out.println("**************************************************************************************************");
                            break;
                        } else
                            System.out.println("Error!! Please enter valid password");
                    }
                try {
                   insertUserDetails(name, emailId, password);
                    System.out.println("Account Successfully created");
                    System.out.println("**************************************************************************************************");
                } catch (SQLException e) {
                    System.out.println("Email is already exist");
                    System.out.println("Enter another Email or try to login");
                    user();
                }
            }
            break;
            case 2: {
                System.out.println("**************************************************************************************************");
                System.out.println("Enter EmailId");
                String emailId = sc.next();
                System.out.println("**************************************************************************************************");
                displayUserDetails();
                Statement st = JukeBox.connection();
                System.out.println("Password (it should contains at least 8 characters)");
                String password=sc.next();

                ResultSet rs= st.executeQuery("select EmailId,password from UserDetails where EmailId='"+emailId+"' AND password='"+password+"'");
                if (rs.next()) {
                        if (rs.getString(2).equals(emailId) && rs.getString(3).equals(password))
                        {
                            System.out.println("**************************************************************************************************");
                            System.out.println("Successfully LoggedIn");
                            System.out.println("**************************************************************************************************");
                            break;
                        }
                    }
                    else {
                        System.out.println("EmailId and Password does not match");
                        user();
                    }
            }
            break;
            default:{
                System.out.println("Invalid Input, Enter credential Again");
                user();

            }
        }
        display();
    }
    public static void createUserDetailsTable() throws SQLException {
        Statement st =JukeBox.connection();
        st.execute("create table if not exists UserDetails(name varchar(20), EmailID varchar(50),password varchar(20),constraint primary key(EmailId))");
    }
    public static void insertUserDetails(String name, String EmailId, String password) throws SQLException {
        Statement st = JukeBox.connection();
        st.executeUpdate("insert into UserDetails values('" + name + "','" + EmailId + "', '"+password+"')");
        }

    public static void displayUserDetails() {
        Statement st = null;
        try {
            st = JukeBox.connection();
            st.executeQuery("select * from UserDetails");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void display() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("**************************************************************************************************");
        System.out.println("Enter your choice");
        System.out.println("1. Display all music");
        System.out.println("2. Search the Songs");
        System.out.println("3. Library");
        System.out.println("0. Exit");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice)
        {
            case 1:
            {
                JukeBox.displayTable();
                System.out.println("**************************************************************************************************");
                System.out.println("Enter you choice");
                System.out.println("Press 1 to play the song");
                System.out.println("Press 0 to go back to Home");
                int choose= sc.nextInt();
                switch (choose)
                {
                    case 1:
                        Song.listenToMusic();
                        break;

                    case 0:
                        display();
                        break;
                }
                break;
            }
            case 2:
            {
                MainMenu.search();
                break;
            }
            case 3:
            {
                MainMenu.library();
                break;
            }
            case 0:
            {
                break;
            }

            default:
            {
                System.out.println("Enter the valid Choice");
                display();
            }
        }
    }
}
