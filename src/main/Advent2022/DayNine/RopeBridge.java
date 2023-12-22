/**
 * Created by: Alyson
 * Date: 1/31/23
 * Time: 12:35 PM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayNine;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class RopeBridge {
    private final List<String> directions = new ArrayList<>(LoadFile.inputFromFile());
    private final HashSet<String> individualPoints = new HashSet<>();
    private final Point head = new Point(0,0);
    private final Point one = new Point(0,0);
    private final Point two = new Point(0,0);
    private final Point three = new Point(0,0);
    private final Point four = new Point(0,0);
    private final Point five = new Point(0,0);
    private final Point six = new Point(0,0);
    private final Point seven = new Point(0,0);
    private final Point eight = new Point(0,0);
    private final Point tail = new Point(0,0);
    private final List<Point> ropeJoints = new ArrayList<>(
        Arrays.asList(head, one, two, three, four, five, six, seven, eight, tail));
    private String direction;
    private int numMoves;

    public static void start() {
        LaunchProgram.launchProgram("one", "two", RopeBridge.class,
            "partOne", "partTwo");
    }

    public static void partOne() {
        RopeBridge ropeBridge = new RopeBridge();
        ropeBridge.setStartLocation();
        ropeBridge.followDirections();
        System.out.println("The number of points the tail visited is : " + ropeBridge.individualPoints.size());
    }

    public static void partTwo() {
        RopeBridge ropeBridge = new RopeBridge();
        ropeBridge.followDirectionsForWholeRope();
        System.out.println("The number of points the tail visited is : " + ropeBridge.individualPoints.size());
    }

    private void setStartLocation() {
        head.setLocation(0,0);
        tail.setLocation(0,0);
        individualPoints.add(tail.x + "," + tail.y);
    }

    private void followDirections() {
        for (String instruction : directions) {
            direction = instruction.substring(0, 1);
            numMoves = Integer.parseInt(instruction.substring(2));
            makeHMoves();
        }
    }

    private void makeHMoves() {
        for (int i = 0; i < numMoves; i++) {
            Point hPrevious = head.getLocation();
            switch (direction) {
                case "L" -> head.translate(-1, 0);
                case "R" -> head.translate(+1, 0);
                case "U" -> head.translate(0, +1);
                case "D" -> head.translate(0, -1);
            }
            if (!isAdjacent()) {
                tail.setLocation(hPrevious);
                individualPoints.add(tail.x + "," + tail.y);
            }
        }
    }

    private boolean isAdjacent() {
        return head.y + 1 == tail.y && head.x == tail.x || head.y + 1 == tail.y && head.x + 1 == tail.x ||
            head.x + 1 == tail.x && head.y == tail.y || head.y - 1 == tail.y && head.x + 1 == tail.x ||
            head.y - 1 == tail.y && head.x == tail.x || head.x - 1 == tail.x && head.y - 1 == tail.y ||
            head.x - 1 == tail.x && head.y == tail.y || head.x - 1 == tail.x && head.y + 1 == tail.y ||
            head.x == tail.x && head.y == tail.y;
    }

    private void followDirectionsForWholeRope() {
        for (String instruction : directions) {
            direction = instruction.substring(0, 1);
            numMoves = Integer.parseInt(instruction.substring(2));
            moveEntireRope();
        }
    }

    private void moveEntireRope() {
        for (int i = 0; i < numMoves; i++) {

            switch (direction) {
                case "L" -> head.translate(-1, 0);
                case "R" -> head.translate(+1, 0);
                case "U" -> head.translate(0, +1);
                case "D" -> head.translate(0, -1);
            }
            for (int j = 1; j < ropeJoints.size(); j++) {
                if (!isJointAdjacent(ropeJoints.get(j - 1), ropeJoints.get(j))) {
                    int previousX = ropeJoints.get(j - 1).x;
                    int currentX = ropeJoints.get(j).x;
                    int previousY = ropeJoints.get(j - 1).y;
                    int currentY = ropeJoints.get(j).y;
                    String directionToMove = checkDirectionToMove(previousX, currentX, previousY, currentY);
                    moveJoint(directionToMove, j);
                    individualPoints.add(tail.x + "," + tail.y);
                }
            }
        }
    }

    private String checkDirectionToMove(int previousX, int currentX, int previousY, int currentY) {
        if (Math.abs(previousX - currentX) > 1 && previousY == currentY) {
            return "column";
        } else if (Math.abs(previousY - currentY) > 1 && previousX == currentX) {
            return "row";
        } else {
            return "diagonal";
        }
    }

    private void moveJoint(String direction, int jointToMove) {
        switch (direction) {
            case "diagonal" -> moveDiagonally(jointToMove);
            case "row" -> moveRow(jointToMove);
            case "column" -> moveColumn(jointToMove);
        }
    }

    private void moveDiagonally(int jointToMove) {
        System.out.println("Diagonal");
        int jointToMoveX = ropeJoints.get(jointToMove).x;
        int jointToMoveY = ropeJoints.get(jointToMove).y;
        int precedingJointX = ropeJoints.get(jointToMove - 1).x;
        int precedingJointY = ropeJoints.get(jointToMove - 1).y;
        if (jointToMoveX > precedingJointX && jointToMoveY > precedingJointY) {
            ropeJoints.get(jointToMove).translate(-1, -1);
        } else if (jointToMoveX < precedingJointX && jointToMoveY < precedingJointY) {
            ropeJoints.get(jointToMove).translate(+1, + 1);
        } else if (jointToMoveX > precedingJointX && jointToMoveY < precedingJointY) {
            ropeJoints.get(jointToMove).translate(-1, +1);
        } else if (jointToMoveX < precedingJointX && jointToMoveY > precedingJointY) {
            ropeJoints.get(jointToMove).translate(+1, -1);
        }
    }

    private void moveColumn(int jointToMove) {
        System.out.println("Column");
        int jointToMoveX = ropeJoints.get(jointToMove).x;
        int precedingJointX = ropeJoints.get(jointToMove - 1).x;
        if (jointToMoveX > precedingJointX) {
            ropeJoints.get(jointToMove).translate(-1, 0);
        } else if (jointToMoveX < precedingJointX) {
            ropeJoints.get(jointToMove).translate(+1, 0);
        }
    }
    private void moveRow(int jointToMove) {
        System.out.println("Row");
        int jointToMoveY = ropeJoints.get(jointToMove).y;
        int precedingJointY = ropeJoints.get(jointToMove - 1).y;
        if (jointToMoveY > precedingJointY) {
            ropeJoints.get(jointToMove).translate(0, -1);
        } else if (jointToMoveY < precedingJointY) {
            ropeJoints.get(jointToMove).translate(0, +1);
        }
    }


    private boolean isJointAdjacent(Point previousJoint, Point currentJoint) {
        return previousJoint.y + 1 == currentJoint.y && previousJoint.x == currentJoint.x ||
            previousJoint.y + 1 == currentJoint.y && previousJoint.x + 1 == currentJoint.x ||
            previousJoint.x + 1 == currentJoint.x && previousJoint.y == currentJoint.y ||
            previousJoint.y - 1 == currentJoint.y && previousJoint.x + 1 == currentJoint.x ||
            previousJoint.y - 1 == currentJoint.y && previousJoint.x == currentJoint.x ||
            previousJoint.x - 1 == currentJoint.x && previousJoint.y - 1 == currentJoint.y ||
            previousJoint.x - 1 == currentJoint.x && previousJoint.y == currentJoint.y ||
            previousJoint.x - 1 == currentJoint.x && previousJoint.y + 1 == currentJoint.y ||
            previousJoint.x == currentJoint.x && previousJoint.y == currentJoint.y;
    }
}
