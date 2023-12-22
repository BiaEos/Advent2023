package main.Advent2022.DaySeven;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;

import java.util.*;

public class DirectorySize {
    private final List<String> terminalOutput = new ArrayList<>(LoadFile.inputFromFile());
    private final Stack<String> directories = new Stack<>();
    private final HashMap<String, Integer> sizeOfDirectories = new HashMap<>();
    private final List<Integer> filesToDelete = new ArrayList<>();
    private int totalSizeUnder1e5 = 0;

    public static void start() {
        LaunchProgram.launchProgram("one", "two", DirectorySize.class,
            "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        DirectorySize directorySize = new DirectorySize();
        directorySize.sortData();
        directorySize.printDayOne();
    }

    public static void startDayTwo() {
        DirectorySize directorySize = new DirectorySize();
        directorySize.sortData();
        directorySize.printDayTwo();
    }

    private void sortData() {
        String addValues = "";
        for (String output : terminalOutput) {
            if (output.startsWith("$ cd")) {
                String folderName = output.substring(5);
                if (output.startsWith("$ cd ..")) {
                    directories.pop();
                } else {
                    directories.push(folderName);
                }
            } else if (output.matches("\\d+\\s\\w+") || output.matches("\\d+\\s\\w+\\.\\w+")) {
                String[] fileParts = output.split("\\s");
                int fileSize = Integer.parseInt(fileParts[0]);
                for (String directory : directories) {
                    addValues = addValues.concat(directory);
                    sizeOfDirectories.merge(addValues, fileSize, Integer::sum);
                }
                addValues = "";
            }
        }
        for (String key : sizeOfDirectories.keySet()) {
            if (sizeOfDirectories.get(key) <= 100_000) {
                totalSizeUnder1e5 += sizeOfDirectories.get(key);
            }
        }
        int sizeOfDrive = 70_000_000;
        int totalSpaceNeeded = 30_000_000;
        int allSpaceUsed = sizeOfDirectories.get("/");
        int freeSpace = sizeOfDrive - allSpaceUsed;
        int sizeToDelete = totalSpaceNeeded - freeSpace;
        for (String key : sizeOfDirectories.keySet()) {
            if (sizeOfDirectories.get(key) >= sizeToDelete) {
                filesToDelete.add(sizeOfDirectories.get(key));
            }
        }
    }

    private void printDayOne() {
        System.out.println("The total size of directories <= 100,000 is: " + totalSizeUnder1e5);
    }

    private void printDayTwo () {
        System.out.println("The smallest file to delete and clear enough space is: " +
            Collections.min(filesToDelete));
    }
}
