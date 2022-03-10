package com.uh635c.task22.view;

import com.uh635c.task22.controller.TagController;
import com.uh635c.task22.model.Tag;

import java.util.Scanner;

public class TagView {
    private final TagController controller = new TagController();
    private final Scanner scanner;

    public TagView(Scanner scanner) {
        this.scanner = scanner;
    }

    private void showAllTags(){
        System.out.println();
        controller.getAll().stream().forEach(System.out::println);
        System.out.println();
        System.out.print("action: ");
    }

    public void startTagApplication(){
        scanner.useDelimiter("\n");

        System.out.print("Enter the required action:\n" +
                "    enter - to look information about a Tag, \n" +
                "    delete - to delete a Tag,\n" +
                "    save - to save a Tag\n" +
                "    update - to update a Tag\n" +
                "    exit - to exit from the list of tags\n");

        showAllTags();

        while(scanner.hasNext()){

            switch(scanner.next()){
                case "enter":
                    System.out.print("Enter ID of the required Tag: ");
                    String idEnter = scanner.next();
                    Tag tag = controller.getById(idEnter);
                    System.out.println(tag);
                    System.out.print("action: ");
                    break;
                case "update":
                    System.out.print("Enter ID of the required Tag: ");
                    String idUpdate = scanner.next();
                    System.out.print("Enter a new NAME of the required Tag: ");
                    String nameUpdate = scanner.next();
                    controller.update(idUpdate, nameUpdate);
                    showAllTags();
                    break;
                case "save":
                    System.out.print("Enter a NAME of a new Tag: ");
                    String nameSave = scanner.next();
                    controller.save(nameSave);
                    showAllTags();
                    break;
                case "delete":
                    System.out.print("Enter ID of the required Tag: ");
                    controller.remove(scanner.next());
                    showAllTags();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command, try again");
                    System.out.println("action: ");
            }
        }
    }
}
