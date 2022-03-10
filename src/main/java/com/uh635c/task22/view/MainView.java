package com.uh635c.task22.view;

import java.util.Scanner;

public class MainView {
    private Scanner userInput = new Scanner(System.in);

    public void showStartApplication(){
        System.out.println("Welcome to CRUD application \nChoose what list of items you want to look at (examples: Tags, Posts or Writers");
        System.out.print("list of: ");

        while(userInput.hasNext()){
            switch(userInput.next()){
                case "Tags":
                    new TagView(userInput).startTagApplication();
                    System.out.print("Choose list of items you want to look at or enter 'exit': ");
                    break;
                case "Posts":
                    new PostView(userInput).startPostApplication();
                    System.out.print("Choose list of items you want to look at or enter 'exit': ");
                    break;
                case "Writers":
                    new WriterView(userInput).startWriterApplication();
                    System.out.print("Choose list of items you want to look at or enter 'exit': ");
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Your input is incorrect, try again.");
                    System.out.print("Choose list of items you want to look at or enter 'exit': ");
            }
        }
    }


}
