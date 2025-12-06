import java.io.File;
import java.util.Scanner;

public class Day1Part2 {
    public static void main(String[] args) throws Exception {
        File currentDir = new File(".");
        File parentDir = currentDir.getParentFile();
        File input = new File(parentDir, "inputs/day-1");
        Scanner reader = new Scanner(input);

        int dial = 50;
        int zeroCount = 0;

        System.out.println("The dial starts by pointing at " + dial + ".");

        while (reader.hasNextLine()) {
            String instruction = reader.nextLine();
            String rotation = instruction.substring(0, 1);
            int magnitude = Integer.parseInt(instruction.substring(1, instruction.length()));
            int localZeroCount = 0;

            if (magnitude > 100) {
                int hundreths = (int) Math.floor(magnitude / 100);
                magnitude = (magnitude - (hundreths * 100));
                localZeroCount = localZeroCount + hundreths;
            }

            if (rotation.equalsIgnoreCase("R")) {
                int initialDial = dial;
                dial = initialDial + magnitude;

                if (dial > 100 && initialDial != 0) {
                    localZeroCount++;
                }
            }

            if (rotation.equalsIgnoreCase("L")) {
                int initialDial = dial;
                dial = initialDial - magnitude;

                if (dial < 0 && initialDial != 0) {
                    localZeroCount++;
                }
            }

            if (dial > 99) {
                dial = dial - 100;
            }

            if (dial < 0) {
                dial = dial + 100;
            }

            if (dial == 0) {
                localZeroCount++;
            }

            System.out.print("The dial is rotated " + instruction + " to point at " +
                    dial);
            if (localZeroCount > 0) {
                System.out.println("; during this rotation, it points at zero " + localZeroCount + " times.");
            } else {
                System.out.println(".");
            }

            zeroCount = zeroCount + localZeroCount;
        }

        System.out.println(
                "\r\nBecause the dial points at 0 a total of three times during this process, the password is "
                        + zeroCount + ".");

        reader.close();
    }
}
