package jankowiak.kamil.service;

import jankowiak.kamil.exceptions.MyException;

import java.util.Scanner;

public class DataUserService {

    private Scanner sc = new Scanner(System.in);

    public int getInt(String message){
        System.out.println(message);

        String text = sc.nextLine();
        if (!text.matches("\\d+")) {
            throw new MyException("Invalid option number");
        }
        return Integer.parseInt(text);
    }

    public void close(){
        if (sc != null){
            sc.close();
            sc = null;
        }
    }
}
