import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day12Test {

    Day12 day12 = new Day12();

    @Test
    public void Day12Part1() {
        Assertions.assertEquals(8419, day12.part1());
    }

    @Test
    public void Day12Part2() {
        System.out.println(day12.part2());
    }

    @Test
    public void Day12Part1Test() {
        Day12 day12Test = new Day12("src/main/Day12Test");
        Assertions.assertEquals(21, day12Test.part1());
    }

    @Test
    public void verifyArrangementPossible() {
        List<Integer> damagedGroup = new ArrayList<>();
        damagedGroup.add(1);
        damagedGroup.add(1);
        damagedGroup.add(3);
        Assertions.assertTrue(day12.isArrangementPossible("#.#.###", damagedGroup));
        Assertions.assertTrue(day12.isArrangementPossible("#.#..###", damagedGroup));
        Assertions.assertTrue(day12.isArrangementPossible("..#.#.###", damagedGroup));
        Assertions.assertTrue(day12.isArrangementPossible("#.#.###..", damagedGroup));
        Assertions.assertFalse(day12.isArrangementPossible("###.###", damagedGroup));
        Assertions.assertFalse(day12.isArrangementPossible("##.#.###", damagedGroup));
    }
}
