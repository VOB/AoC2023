import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class Day6Test {
    Day6 day6 = new Day6();

    @Test
    public void day6Part1() {
        Assertions.assertEquals(211904, day6.part1());
    }

    @Test
    public void day6Part2() {
        Assertions.assertEquals(43364472, day6.part2());
    }

    @Test
    public void singleRace() {
        System.out.println(day6.getNumberOfPossibleWaysToWin(BigInteger.valueOf(7), BigInteger.valueOf(9)));
    }
}
