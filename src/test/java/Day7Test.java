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
        Assertions.assertEquals(7, day7.handType("AAAAA"));
        Assertions.assertEquals(6, day7.handType("AA2AA"));
        Assertions.assertEquals(6, day7.handType("AAAA2"));
        Assertions.assertEquals(5, day7.handType("A33AA"));
        Assertions.assertEquals(4, day7.handType("A23AA"));
        Assertions.assertEquals(3, day7.handType("AAKK3"));
        Assertions.assertEquals(2, day7.handType("AAK53"));
        Assertions.assertEquals(1, day7.handType("2345K"));

    }
}
