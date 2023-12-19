import java.util.List;

public class Day14 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day14() {
        this.input = textParser.lines("src/main/Day14");
    }

    public Day14(String path) {
        this.input = textParser.lines(path);
    }

    public int part1() {
        for (int i=1; i<input.size(); i++) {
            for (int j=0; j<input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'O' ) {
                    slideRockNorth(i, j);
                }
            }
        }

        int totalLoad = 0;
        for (int i=0; i< input.size(); i++) {
            int rockCount = (int) input.get(i).chars().filter(ch -> ch == 'O').count();
            totalLoad += rockCount * (input.size()-i);
        }
        return totalLoad;
    }

    private void slideRockNorth(int i, int j) {
        boolean spaceAbove = input.get(i-1).charAt(j) == '.';
        if (spaceAbove) {
            input.set(i-1, input.get(i-1).substring(0, j) + 'O' + input.get(i-1).substring(j+1));
            input.set(i, input.get(i).substring(0, j) + '.' + input.get(i).substring(j+1));
        }
        if (i > 1 && spaceAbove) {
            slideRockNorth(i-1, j);
        }
    }

    public int part2() {
        return 0;
    }
}
