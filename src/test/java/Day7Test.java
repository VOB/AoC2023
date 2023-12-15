import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day7Test {
    Day7 day7 = new Day7();

    @Test
    public void Day7Part1() {
        System.out.println(day7.part1());
        Assertions.assertEquals(245794640, day7.part1());
    }

    @Test
    public void Day7Part2() {
        System.out.println(day7.part2());
    }

    @Test
    public void verifyHandTypes() {
        List<String> hands = new ArrayList<>();
        hands.add("AAAAA");
        hands.add("AA2AA");
        hands.add("A33AA");
        hands.add("A23AA");
        hands.add("AAKK3");
        hands.add("AAK53");
        hands.add("2345K");
        Assertions.assertEquals(7, day7.getHandType("AAAAA"));
        Assertions.assertEquals(6, day7.getHandType("AA2AA"));
        Assertions.assertEquals(6, day7.getHandType("AAAA2"));
        Assertions.assertEquals(5, day7.getHandType("A33AA"));
        Assertions.assertEquals(4, day7.getHandType("A23AA"));
        Assertions.assertEquals(3, day7.getHandType("AAKK3"));
        Assertions.assertEquals(3, day7.getHandType("3AAKK"));
        Assertions.assertEquals(2, day7.getHandType("AAK53"));
        Assertions.assertEquals(2, day7.getHandType("KAA53"));
        Assertions.assertEquals(2, day7.getHandType("KAA53"));
        Assertions.assertEquals(2, day7.getHandType("KQAA3"));
        Assertions.assertEquals(2, day7.getHandType("KQ5AA"));
        Assertions.assertEquals(1, day7.getHandType("2345K"));

    }
}
