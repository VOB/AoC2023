import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day5 {

    TextParser textParser = new TextParser();
    List<List<String>> input;

    public Day5() {
        input = textParser.blocks("src/main/Day5");
    }

    public Day5(String input) {
        this.input = textParser.blocks(input);
    }

    public BigInteger part1() {
        List<BigInteger> seeds = extractSeeds();
        BigInteger lowestLocation = BigInteger.ZERO;
        for (BigInteger seed : seeds) {
            BigInteger locationNumber = getLocationNumber(seed);
            if (lowestLocation.equals(BigInteger.ZERO)) {
                lowestLocation = locationNumber;
            } else if (locationNumber.compareTo(lowestLocation) < 0) {
                lowestLocation = locationNumber;
            }
        }
        return lowestLocation;
    }

    public BigInteger part2() {
        List<BigInteger> seeds = extractSeeds();

        BigInteger lowestLocation = BigInteger.ZERO;
        for (int i = 0; i < seeds.size(); i+=2) {
            BigInteger seed = seeds.get(i);
            BigInteger range = seeds.get(i+1);
            System.out.println("getting lowest for " + seed + " with range " + range);
            BigInteger lowestLocationForRange = getLowestLocationNumberForRange(seed, range);
            System.out.println(lowestLocationForRange);
            if (lowestLocation.equals(BigInteger.ZERO)) {
                lowestLocation = lowestLocationForRange;
            } else if (lowestLocationForRange.compareTo(lowestLocation) < 0) {
                lowestLocation = lowestLocationForRange;
            }
        }
        return lowestLocation;
    }

    public BigInteger getLowestLocationNumberForRange(BigInteger seed, BigInteger range) {
        BigInteger lowestLocation = BigInteger.ZERO;

        BigInteger iterator = BigInteger.ZERO;
        while (iterator.compareTo(range) < 0) {
            BigInteger locationNumber = getLocationNumber(seed.add(iterator));
            if (lowestLocation.equals(BigInteger.ZERO)) {
                lowestLocation = locationNumber;
            } else if (locationNumber.compareTo(lowestLocation) < 0) {
                lowestLocation = locationNumber;
                System.out.println(locationNumber);
            }
            iterator = iterator.add(BigInteger.ONE);
        }
        return lowestLocation;
    }

    public BigInteger getLocationNumber(BigInteger seed) {
        for (int i = 1; i < input.size(); i++) {
            seed = findNextMap(seed, i);
        }
        return seed;
    }

    public BigInteger findNextMap(BigInteger previous, int nextBlock) {

        List<String> block = input.get(nextBlock).subList(1,input.get(nextBlock).size());

        for (String mapping : block) {
            List<BigInteger> values = extractFields(mapping);
            BigInteger destinationRangeStart = values.get(0);
            BigInteger sourceRangeStart = values.get(1);
            BigInteger rangeLength = values.get(2);

            if (sourceRangeStart.compareTo(previous) <= 0 && previous.compareTo(sourceRangeStart.add(rangeLength)) <= 0) {
                return destinationRangeStart.add(previous.subtract(sourceRangeStart));
            }
        }


        return previous;
    }

    public List<BigInteger> extractSeeds() {
        List<String> seeds = Arrays.asList(input.get(0).get(0).split(": ")[1].split(" "));
        return seeds.stream()
                .map(String::trim)
                .map(BigInteger::new)
                .collect(Collectors.toList());
    }

    private List<BigInteger> extractFields(String mapping) {
        return Arrays.stream(mapping.split(" ")).map(BigInteger::new).collect(Collectors.toList());
    }
}
