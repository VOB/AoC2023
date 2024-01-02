import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    TextParser textParser = new TextParser();
    List<List<String>> input;

    public Day13() {
        input = textParser.blocks("src/main/Day13");
    }
    public Day13(String path) {
        input = textParser.blocks(path);
    }

    public int part1() {

        int sum = 0;

        for (List<String> pattern : input) {
            char[][] charPattern = new char[pattern.size()][pattern.get(0).length()];
            for (int i=0; i<pattern.size(); i++) {
                charPattern[i] = pattern.get(i).toCharArray();
            }
            sum += getReflectionValue(charPattern)*100 + getReflectionValue(transpose(charPattern));

        }

        return sum;
    }

    public int part2() {
        int sum = 0;

        for (List<String> pattern : input) {
            char[][] charPattern = new char[pattern.size()][pattern.get(0).length()];
            for (int i=0; i<pattern.size(); i++) {
                charPattern[i] = pattern.get(i).toCharArray();
            }

            int horizontalReflection = getReflectionValue(charPattern);
            int verticalReflection = getReflectionValue(transpose(charPattern));
            List<Integer> horizontalReflectionValues = getReflectionValues(charPattern);
            List<Integer> verticalReflectionValues = getReflectionValues(transpose(charPattern));

            if (horizontalReflection > 0) {
                removeIntFromListOfInts(horizontalReflection, horizontalReflectionValues);
            }
            if (verticalReflection > 0) {
                removeIntFromListOfInts(verticalReflection, verticalReflectionValues);
            }

            if (!horizontalReflectionValues.isEmpty()) {
                sum += horizontalReflectionValues.get(0)*100;
            } else if (!verticalReflectionValues.isEmpty()){
                sum += verticalReflectionValues.get(0);
            }

        }

        return sum;
    }

    private int getReflectionValue(char[][] pattern) {
        for (int i = pattern.length-1; i>0; i--) {
            char[] first = pattern[i];
            char[] second = pattern[i-1];

            if(Arrays.equals(first, second)) {
                if (verifyReflection(pattern, i, i-1, 0)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private List<Integer> getReflectionValues(char[][] pattern) {
        List<Integer> reflectionValues = new ArrayList<>();
        for (int i = pattern.length-1; i>0; i--) {
            if (verifyReflection(pattern, i, i-1, 1)) {
                reflectionValues.add(i);
            }
        }
        return reflectionValues;
    }

    private boolean verifyReflection(char[][] pattern, int high, int low, int tolerance) {
        if (tolerance < 0) {
            return false;
        }
        if (high > pattern.length-1 || low < 0) {
            return true;
        }
        if (Arrays.equals(pattern[high], pattern[low])) {
            return verifyReflection(pattern, high+1, low-1, tolerance);
        } else {
            int diff = 0;
            for (int i=0; i<pattern[high].length; i++) {
                if (!(pattern[high][i] == pattern[low][i])) {
                    diff++;
                }
            }
            return verifyReflection(pattern, high+1, low-1, tolerance-diff);
        }
    }

    private List<Integer> removeIntFromListOfInts(int num, List<Integer> list) {
        for (int i=0; i<list.size(); i++) {
            if (num == list.get(0)) {
                list.remove(0);
            }
        }
        return list;
    }

    public char[][] transpose(char[][] array) {
        // empty or unset array, nothing do to here
        if (array == null || array.length == 0)
            return array;

        int width = array.length;
        int height = array[0].length;

        char[][] array_new = new char[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = array[x][y];
            }
        }
        return array_new;
    }
}
