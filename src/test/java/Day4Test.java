import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day4Test {

    Day4 day4 = new Day4();

    @Test
    public void day4Part1() {
        Assertions.assertEquals(24733, day4.part1());
    }

    @Test
    public void day4Part2() {
        Assertions.assertEquals(5422730, day4.part2());
    }

    @Test
    public void day4Part2Test() {
        Day4 day4Test = new Day4("src/main/Day4Test");
        Assertions.assertEquals(30, day4Test.part2());
    }
}
