package ru.itis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Data> dataList = new ArrayList<>(generateData());
        List<User> userList = new ArrayList<>(generateUser());

        Scanner scanner = new Scanner(System.in);
        String quit = "";
        while (!quit.equals("quit")) {
            System.out.println("");
            System.out.println("Select user (write just number):");
            for (User u : userList) {
                System.out.print(u.name + " ");
            }
            System.out.println("");
            quit = scanner.next();
            if (quit.equals("quit")) {
                System.out.println("Good bye!");
                continue;
            }
            int selected_user = Integer.parseInt(quit);
            if (selected_user > 8) {
                System.out.println("Error. Try again");
                continue;
            }
            // if selected root
            if (selected_user == 0) {
                System.out.println("Root" + selected_user + " selected successfully!");
                System.out.println("Choose action: ch_data, ch_data_ac, ch_user_ac");
                quit = scanner.next();
                if (quit.equals("quit")) {
                    System.out.println("Good bye!");
                    continue;
                }
                if (quit.equals("ch_data")) {
                    for (Data d : dataList) {
                        System.out.println("File name: " + d.name + " Access: Read, Write | Data: " + d.data);
                    }
                    System.out.println("Write file number:");
                    int id = scanner.nextInt();
                    System.out.println("File name: " + dataList.get(id - 1).name + " Data: " + dataList.get(id - 1).data);
                    System.out.println("Write smth here to change data: ");
                    dataList.get(id - 1).setData(scanner.next());
                    System.out.println("File name: " + dataList.get(id - 1).name + " New Data: " + dataList.get(id - 1).data);
                }
                if (quit.equals("ch_data_ac")) {
                    for (Data d : dataList) {
                        System.out.println("File name: " + d.name + " Access mark: " + d.access_mark);
                    }
                    System.out.println("Write file number:");
                    int file_id = scanner.nextInt();
                    System.out.println("Write new mark between 1 to 8");
                    dataList.get(file_id - 1).setAccess_mark(scanner.nextInt());
                    System.out.println("File name: " + dataList.get(file_id - 1).name + " New Access mark: " + dataList.get(file_id - 1).access_mark);
                }
                if (quit.equals("ch_user_ac")) {
                    for (User u : userList) {
                        System.out.println("User name: " + u.name + " Access lv: " + u.access_lv);
                    }
                    System.out.println("Write user id:");
                    int user_id = scanner.nextInt();
                    System.out.println("Write new mark between 1 to 8");
                    userList.get(user_id).setAccess_lv(scanner.nextInt());
                    System.out.println("User name: " + userList.get(user_id).name + " New Access lv: " + userList.get(user_id).access_lv);
                }
                continue;
            }

            System.out.println("User" + selected_user + " selected successfully! Files in system:");
            for (Data d : dataList) {
                if (userList.get(selected_user).access_lv  == d.access_mark) {
                    System.out.println("File name: " + d.name + " Access: Read, Write");
                    continue;
                }
                if (userList.get(selected_user).access_lv > d.access_mark) {
                    System.out.println("File name: " + d.name + " Access: Read");
                    continue;
                }
                System.out.println("File name: " + d.name + " Access: -");
            }
            System.out.println("Choose file (just number) to change data:");
            quit = scanner.next();
            if (quit.equals("quit")) {
                System.out.println("Good bye!");
                continue;
            }
            int selected_file = Integer.parseInt(quit);
            if (selected_file > 5) {
                System.out.println("Error. Try again");
                continue;
            }
            if (userList.get(selected_user).access_lv == dataList.get(selected_file - 1).access_mark) {
                System.out.println("File name: " + dataList.get(selected_file - 1).name + " Data: " + dataList.get(selected_file - 1).data);
                System.out.println("Write smth here to change data: ");
                quit = scanner.next();
                if (quit.equals("quit")) {
                    System.out.println("Good bye!");
                    continue;
                }
                dataList.get(selected_file - 1).setData(quit);
                System.out.println("File name: " + dataList.get(selected_file - 1).name + " New Data: " + dataList.get(selected_file - 1).data);
            }
            if (userList.get(selected_user).access_lv > dataList.get(selected_file - 1).access_mark) {
                System.out.println("You can not change data. Just read:");
                System.out.println("File name: " + dataList.get(selected_file - 1).name + " Data: " + dataList.get(selected_file - 1).data);
            }
            if (userList.get(selected_user).access_lv < dataList.get(selected_file - 1).access_mark) {
                System.out.println("Sorry, no access");
            }
        }

    }

    public static List<Data> generateData() {
        List<Data> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(new Data("file" + i, i, "some data"));
        }
        return list;
    }

    public static List<User> generateUser() {
        List<User> list = new ArrayList<>();
        list.add(new User("root", 0));
        for (int i = 1; i <= 8; i++) {
            list.add(new User("user" + i, i));
        }
        return list;
    }
}
