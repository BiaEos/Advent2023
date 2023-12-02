package Tools;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class LaunchProgram {
    public static <T> void launchProgram(String optionOne, String optionTwo, Class<T> myClass,
                                         String methodOneName, String methodTwoName) {

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want part " + optionOne + " or part " + optionTwo + " ?");
        String answer = input.next();
        if (answer.equalsIgnoreCase(optionOne)) {
            try {
                myClass.getDeclaredMethod(methodOneName).invoke(null);
            } catch (NoSuchMethodException e) {
                System.out.println("No such method exception thrown from day one, " +
                        "check the start method in your day's named class.");
            } catch (InvocationTargetException e) {
                System.out.println("Invocation target exception thrown from day one, " +
                        "check the start method in your day's named class.");
            } catch (IllegalAccessException e) {
                System.out.println("Illegal access exception thrown from day one, " +
                        "check the start method in your day's named class.");
            }
        } else if (answer.equalsIgnoreCase(optionTwo)) {
            try {
                myClass.getDeclaredMethod(methodTwoName).invoke(null);
            } catch (NoSuchMethodException e) {
                System.out.println("No such method exception thrown from day two, " +
                        "check the start method in your day's named class.");
            } catch (InvocationTargetException e) {
                System.out.println("Invocation target exception thrown from day two, " +
                        "check the start method in your day's named class.");
            } catch (IllegalAccessException e) {
                System.out.println("Illegal access exception thrown from day two, " +
                        "check the start method in your day's named class.");
            }
        } else if (answer.equalsIgnoreCase("Exit")) {
            System.exit(0);
        } else {
            System.out.println("Please enter " + optionOne + ", " + optionTwo + ", or Exit" );
            launchProgram(optionOne, optionTwo, myClass, methodOneName, methodTwoName);
        }
    }
}
