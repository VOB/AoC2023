import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day18Test {

    Day18 day18 = new Day18();

    @Test
    public void Day18Part1() {
       Assertions.assertEquals(45159, day18.part1());
    }

    //@Test
    public void Day18Part2() {
        System.out.println(day18.part2());
    }

    @Test
    public void Day18Part1Test() {
        Day18 day18Test = new Day18("src/main/Day18Test");
        Assertions.assertEquals(62, day18Test.part1());
    }
}
