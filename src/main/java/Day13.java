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

            sum += horizontalReflection(charPattern) + verticalReflection(charPattern);

        }

        return sum;
    }

    public int horizontalReflection(char[][] pattern) {
        return 100*getReflectionValue(pattern);
    }

    public int verticalReflection(char[][] pattern) {
        char[][] transposed = transpose(pattern);
        return getReflectionValue(transposed);
    }

    private int getReflectionValue(char[][] pattern) {
        for (int i = pattern.length-1; i>0; i--) {
            char[] first = pattern[i];
            char[] second = pattern[i-1];

            if(Arrays.equals(first, second)) {
                if (verifyReflection(pattern, i, i-1)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private boolean verifyReflection(char[][] pattern, int high, int low) {
        if (high > pattern.length-1 || low < 0) {
            return true;
        }
        if (Arrays.equals(pattern[high], pattern[low])) {
            return verifyReflection(pattern, high+1, low-1);
        }
        return false;
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

    public int part2() {
        return 0;
    }
}
