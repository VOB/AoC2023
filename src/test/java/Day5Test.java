import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class Day5Test {

    Day5 day5 = new Day5();

    @Test
    public void day5Part1() {
        Assertions.assertEquals(BigInteger.valueOf(525792406), day5.part1());
    }

    @Test
    public void verifyFirstSeedGetsItsLowestLocationTest() {
        Day5 day5Test = new Day5("src/main/Day5Test");
        Assertions.assertEquals(BigInteger.valueOf(82), day5Test.getLocationNumber(day5Test.extractSeeds().get(0)));
    }

    @Test
    public void verifyLowestLocationNumberInTest() {
        Day5 day5Test = new Day5("src/main/Day5Test");
        Assertions.assertEquals(BigInteger.valueOf(35), day5Test.part1());
    }
}
