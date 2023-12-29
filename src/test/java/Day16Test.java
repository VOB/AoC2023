import org.junit.jupiter.api.Test;

public class Day16Test {

    Day16 day16 = new Day16();

    @Test
    public void Day16Part1() {
        System.out.println(day16.part1());
    }

    //@Test
    public void Day16Part2() {
    //    System.out.println(day16.part2());
    }

    @Test
    public void Day16Part1Test() {
        Day16 day16Test = new Day16("src/main/Day16Test");
        System.out.println(day16Test.part1());
    }
}
