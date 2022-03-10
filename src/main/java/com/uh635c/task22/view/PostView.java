package com.uh635c.task22.view;

import com.uh635c.task22.controller.PostController;
import com.uh635c.task22.model.Post;

import java.util.Scanner;

public class PostView {
    private PostController controller = new PostController();
    private Scanner scanner;

    public PostView(Scanner scanner) {
        this.scanner = scanner;
    }

    private void showAllPosts(){
        System.out.println();
        controller.getAll().forEach(p -> System.out.println("Post{id= "+p.getId()+", content= "+p.getContent()+"}"));
        System.out.println();
        System.out.print("action: ");
    }

    public void startPostApplication(){
        scanner.useDelimiter("\n");

        System.out.print("Enter the required action:\n" +
                "    enter - to look information about a Post, \n" +
                "    delete - to delete a Post,\n" +
                "    save - to save a Post\n" +
                "    update - to update a Post\n" +
                "    exit - to exit from the list of posts\n");

        showAllPosts();

        while(scanner.hasNext()){
            switch(scanner.next()){
                case "enter":
                    System.out.print("Enter ID of the required Post: ");
                    String idEnter = scanner.next();
                    Post post = controller.getById(idEnter);
                    System.out.println(post);
                    System.out.print("action: ");
                    break;
                case "update":
                    System.out.print("Enter ID of the required Post: ");
                    String idUpdate = scanner.next();
                    System.out.print("Enter a new CONTENT of the required Post: ");
                    String contentU = scanner.next();
                    System.out.println("Enter updated TAGs for the post using \"', '\" as a delimiter: ");
                    String tagsU = scanner.next();
                    System.out.println("Enter writer`s ID who wrote the updated post");
                    String writerIdU = scanner.next();
                    System.out.println("Enter status of the Post (example ACTIVE or DELETED)");
                    String statusU = scanner.next();
                    controller.update(idUpdate, contentU, tagsU, writerIdU, statusU);
                    showAllPosts();
                    break;
                case "save":
                    System.out.print("Enter a CONTENT of the new Post: ");
                    String contentS = scanner.next();
                    System.out.println("Enter TAGs for the new Post using \"', '\" as a delimiter: ");
                    String tagsS = scanner.next();
                    System.out.println("Enter writer`s ID who wrote the new Post");
                    String writerIdS = scanner.next();
                    System.out.println("Enter STATUS of the new Post (example ACTIVE or DELETED)");
                    String statusS = scanner.next();
                    controller.save(contentS, tagsS, writerIdS, statusS);
                    showAllPosts();
                    break;
                case "delete":
                    System.out.print("Enter ID of the required Post: ");
                    controller.remove(scanner.next());
                    showAllPosts();
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
