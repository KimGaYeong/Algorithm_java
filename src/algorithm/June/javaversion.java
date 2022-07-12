package algorithm.June;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class javaversion {

    public static void main(String args[]) {
        try (FileInputStream fin = new FileInputStream("info.xml");
             BufferedReader br = new BufferedReader(new InputStreamReader(fin));) {
            if (br.ready()) {
                String line1 = br.readLine();
                System.out.println(line1);
            }
        } catch (Exception ex) {
            System.out.println("error");
        }

        LocalDate today = LocalDate.now();
        System.out.println("올해는 " + today.getYear() + "년입니다.");


    }


}

