import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;

public class Day1 {

    TextParser textParser = new TextParser();
    List<String> input;

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

    public int extractNumberFromLine(String line) {
        return Integer.parseInt(String.valueOf(findFirstNumberAsChar(line)) +
                findFirstNumberAsChar(reverseString(line)));
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
}
