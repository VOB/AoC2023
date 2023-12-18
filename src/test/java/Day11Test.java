import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day11Test {

    Day11 day11 = new Day11();

    @Test
    public void Day11Part1() {
        Assertions.assertEquals(9599070, day11.part1());
    }

    @Test
    public void Day11Part2() {
        System.out.println(day11.part2());
    }

    @Test
    public void assertThatGalaxyExpandsCorrectly() {
        Day11 day11Test = new Day11("src/main/Day11Test");
        List<String> expectedGalaxy = new ArrayList<>();
        expectedGalaxy.add("..x#.x..x.");
        expectedGalaxy.add("..x..x.#x.");
        expectedGalaxy.add("#.x..x..x.");
        expectedGalaxy.add("xxxxxxxxxx");
        expectedGalaxy.add("..x..x#.x.");
        expectedGalaxy.add(".#x..x..x.");
        expectedGalaxy.add("..x..x..x#");
        expectedGalaxy.add("xxxxxxxxxx");
        expectedGalaxy.add("..x..x.#x.");
        expectedGalaxy.add("#.x.#x..x.");

        Assertions.assertEquals(expectedGalaxy, day11Test.input);
    }

    @Test
    public void Day11Part1Test() {
        Day11 day11Test = new Day11("src/main/Day11Test");
        Assertions.assertEquals(374, day11Test.part1());
    }

    @Test
    public void Day11Part2Test10() {
        Day11 day11Test = new Day11("src/main/Day11Test");
        Assertions.assertEquals(1030, day11Test.getSumOfDistanceBetweenGalaxies(10));
    }
    @Test
    public void Day11Part2Test100() {
        Day11 day11Test = new Day11("src/main/Day11Test");
        Assertions.assertEquals(8410, day11Test.getSumOfDistanceBetweenGalaxies(100));
    }
}
