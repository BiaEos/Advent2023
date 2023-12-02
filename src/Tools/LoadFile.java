/**
 * Created by: Alyson
 * Date: 12/14/22
 * Time: 7:51 AM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/

package Tools;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class LoadFile {
    private static File fileToUse;
    private static File fileToUse2DArray;
    private static int lineCount = 0;
    private static int lineCountB = 0;
    private static int columnCountB = 0;
    private static int widthOfInput = 0;
    private static final List<String> inputFromFile = new ArrayList<>();
    private static String[][] inputFromFile2DArray;

    private static Stream<String> inputStream = inputFromFile().stream();

    public static void loadFile(String fileName) {
        LoadFile loadFile = new LoadFile();
        fileToUse = new File(fileName);
        loadFile.countFileLines();
        loadFile.createArrayFromFile();
    }

    private void countFileLines() {
        Scanner scannerLineCount;
        try {
            scannerLineCount = new Scanner(fileToUse);
            while (scannerLineCount.hasNextLine()) {
                lineCount++;
                scannerLineCount.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void createArrayFromFile() {
        Scanner scannerAdd;
        try {
            scannerAdd = new Scanner(fileToUse);
            for (int i = 0; i < lineCount; i++) {
                String nextString = scannerAdd.nextLine();
                inputFromFile.add(i, nextString);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static List<String> inputFromFile() {
        return inputFromFile;
    }

    public static void loadFileTo2DArray(String fileName, int columns, int widthOfIndex) {
        LoadFile loadFile = new LoadFile();
        fileToUse2DArray = new File(fileName);
        columnCountB = columns;
        widthOfInput = widthOfIndex;
        loadFile.countFileLines2DArray();
        loadFile.create2DArrayFromFileB();
    }

    private void countFileLines2DArray() {
        Scanner scannerLineCountB;
        try {
            scannerLineCountB = new Scanner(fileToUse2DArray);
            while (scannerLineCountB.hasNextLine()) {
                lineCountB++;
                scannerLineCountB.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void create2DArrayFromFileB() {
        Scanner scannerAddLine;
        inputFromFile2DArray = new String[lineCountB][columnCountB];
        try {
            scannerAddLine = new Scanner(fileToUse2DArray);
            int j = 0;
            for (int i = 0; i < lineCountB; i++) {
                String nextLine = scannerAddLine.nextLine();
                for (int k = 0; k < columnCountB * widthOfInput; k += widthOfInput) {
                    String nextIndex = nextLine.substring(k + 1, ((k + widthOfInput) - 2));
                    inputFromFile2DArray[i][j] = nextIndex;
                    if (j == columnCountB - 1) {
                        j = 0;
                    } else {
                        j++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String[][] inputFromFile2DArray() {
        return inputFromFile2DArray;
    }
}
