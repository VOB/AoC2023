import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;

public class Day1 {

    TextParser textParser = new TextParser();
    List<String> input;
    String[] digits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public Day1() {
        input = textParser.lines("src/main/Day1");
    }

    public int part1() {
        int sum = 0;
        for (String line : input) {
            sum = sum + extractNumberFromLine(line);
        }
        return sum;
    }

    public int part2() {
        int sum = 0;
        for (String line : input) {
            sum = sum + extractDecodedNumberFromLine(line);
        }
        return sum;

    }

    public String decodeLine(String line) {
        for (String digit : digits) {
            if(line.contains(digit)) {
                String newLine = line.replaceFirst(digit, "" + Digit.valueOf(digit).value);
                return decodeLine(newLine);
            }
        };
        return line;
    }

    public String decodeReverseLine(String line) {
        for (String digit : digits) {
            if(line.contains(reverseString(digit))) {
                String newLine = line.replaceFirst(reverseString(digit), reverseString("" + Digit.valueOf(digit).value));
                return decodeReverseLine(newLine);
            }
        };
        return line;
    }

    public int extractNumberFromLine(String line) {
        return Integer.parseInt(String.valueOf(findFirstNumberAsChar(line)) +
                findFirstNumberAsChar(reverseString(line)));
    }

    public int extractDecodedNumberFromLine(String line) {
        return Integer.parseInt(String.valueOf(findFirstNumberAsChar(decodeLine(line))) +
                findFirstNumberAsChar(decodeReverseLine(reverseString(line))));
    }
    private char findFirstNumberAsChar (String line) {
        char[] chars = line.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                return c;
            }
        }
        return 'a';
    }

    private String reverseString (String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        return sb.reverse().toString();
    }

    public enum Digit {
        one(1),
        two(2),
        three(3),
        four(4),
        five(5),
        six(6),
        seven(7),
        eight(8),
        nine(9);

        public final int value;

        private Digit(int value) {
            this.value = value;
        }
    }
}
