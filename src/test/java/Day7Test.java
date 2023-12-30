import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day7Test {
    Day7 day7 = new Day7();

    @Test
    public void Day7Part1() {
        Assertions.assertEquals(245794640, day7.part1());
    }

    @Test
    public void Day7Part2() {
        Assertions.assertEquals(247899149, day7.part2());
    }

    @Test
    public void Day7Part1Test() {
        Day7 day7Test = new Day7("src/main/Day7Test");
        Assertions.assertEquals(6440, day7Test.part1());
    }

    @Test
    public void Day7Part2Test() {
        Day7 day7Test = new Day7("src/main/Day7Test");
        Assertions.assertEquals(5905, day7Test.part2());
    }
}
