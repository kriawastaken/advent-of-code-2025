import java.io.File;
import java.util.Scanner;

public class Day1Part1 {
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

            // 100 rotations = same spot we started at...
            // so we can just remove them.
            if (magnitude > 100) {
                int hundreths = (int) Math.floor(magnitude / 100);
                magnitude = (magnitude - (hundreths * 100));
            }

            if (rotation.equalsIgnoreCase("R")) {
                dial = dial + magnitude;
            }

            if (rotation.equalsIgnoreCase("L")) {
                dial = dial - magnitude;
            }

            // clean up e.g. a rotation of +23 from 97,
            // causing dial to equal 120, by subtracting
            // a hundred so dial equals 20.
            if (dial > 99) {
                dial = dial - 100;
            }

            // clean up e.g. a rotation of -14 from 6,
            // causing dial to equal -8, by adding
            // a hundred so dial equals 92.
            if (dial < 0) {
                dial = dial + 100;
            }

            if (dial == 0) {
                zeroCount++;
            }

            System.out.println("The dial is rotated " + instruction + " to point at " +
                    dial + ".");
        }

        System.out.println(
                "\r\nBecause the dial points at 0 a total of three times during this process, the password is "
                        + zeroCount + ".");

        reader.close();
    }
}
