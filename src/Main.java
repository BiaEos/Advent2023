/**
 * Created by: Alyson
 * Date: 12/19/22
 * Time: 7:53 AM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/

import java.util.Scanner;

import static Advent2023.Day01.Day01.day01;
import static Advent2023.Day02.Day02.day02;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type day number (ex. 1, 2, 3): ");
        int answer = scanner.nextInt();
/*        switch (answer) {
            case 1 -> dayOne();
            case 2 -> dayTwo();
            case 3 -> dayThree();
            case 4 -> dayFour();
            case 5 -> dayFive();
            case 6 -> daySix();
            case 7 -> daySeven();
            case 8 -> dayEight();
            case 9 -> dayNine();
            case 10 -> dayTen();
            case 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 ->
                System.out.println("This day is not yet created");
            default -> System.out.println("Please enter a valid day number.");
        }*/

        switch (answer) {
            case 1 -> day01();
            case 2 -> day02();
            case 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 ->
                    System.out.println("This day is not yet created");
            default -> System.out.println("Please enter a valid day number.");
        }
        scanner.close();
    }
}