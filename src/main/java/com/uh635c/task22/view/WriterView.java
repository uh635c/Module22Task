package com.uh635c.task22.view;

import com.uh635c.task22.controller.WriterController;
import com.uh635c.task22.model.Post;
import com.uh635c.task22.model.Writer;

import java.util.Scanner;

public class WriterView {
    private WriterController controller = new WriterController();
    private Scanner scanner;

    public WriterView(Scanner scanner) {
        this.scanner = scanner;
    }

    private void showAllWriters() {
        System.out.println();
        controller.getAll().forEach(w -> System.out.println("Writer{id= " + w.getId() + ", name= " + w.getName() + "}"));
        System.out.println();
        System.out.print("action: ");
    }

    public void startWriterApplication() {
        scanner.useDelimiter("\n");

        System.out.print("Enter the required action:\n" +
                "    enter - to look information about a Writer, \n" +
                "    delete - to delete a Writer,\n" +
                "    save - to save a Writer\n" +
                "    update - to update a Writer\n" +
                "    exit - to exit from the list of writers\n");

        showAllWriters();

        while (scanner.hasNext()) {
            switch (scanner.next()) {
                case "enter":
                    System.out.print("Enter ID of the required Writer: ");
                    String idEnter = scanner.next();
                    Writer writer = controller.getById(idEnter);
                    System.out.println(writer);
                    System.out.print("action: ");
                    break;
                case "update":
                    System.out.print("Enter ID of the required Writer: ");
                    String idUpdate = scanner.next();
                    System.out.print("Enter new NAME of the required Writer: ");
                    String nameU = scanner.next();
                    controller.update(idUpdate, nameU);
                    showAllWriters();
                    break;
                case "save":
                    System.out.print("Enter a NAME of the new Writer: ");
                    String nameS = scanner.next();
                    controller.save(nameS);
                    showAllWriters();
                    break;
                case "delete":
                    System.out.print("Enter ID of the required Writer: ");
                    controller.remove(scanner.next());
                    showAllWriters();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command, try again");
                    System.out.print("action: ");
            }
        }


    }
}
