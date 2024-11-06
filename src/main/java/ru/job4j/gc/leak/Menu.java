package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    // run - 30 s - 2   create 100 posts - 30 s - 4   delete posts - 30 s - create 100 p - 1 m
    private static final Integer ADD_POST = 1;
    private static final Integer ADD_MANY_POST = 2;
    private static final Integer SHOW_ALL_POSTS = 3;
    private static final Integer DELETE_POST = 4;

    private final String select = "Выберите меню";
    private final String count = "Выберите количество создаваемых постов";
    private final String textOfPost = "Введите текст";
    private final String exit = "Конец работы";
    private final String idForDelete = "Все посты удалены";

    private final String menu = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        Menu menu = new Menu();
        menu.start(commentGenerator, scanner, userGenerator, postStore);
    }

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                System.out.println(count);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                System.out.println(idForDelete);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(exit);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}