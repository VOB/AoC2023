import java.util.Arrays;
import java.util.List;
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

    public long part1() {
        List<Long> seeds = extractSeeds();
        long lowestLocation = 0;
        for (long seed : seeds) {
            long locationNumber = getLocationNumber(seed);
            if (lowestLocation == 0) {
                lowestLocation = locationNumber;
            } else if (locationNumber < lowestLocation) {
                lowestLocation = locationNumber;
            }
        }
        return lowestLocation;
    }

    public long part2() {
        List<Long> seeds = extractSeeds();

        long lowestLocation = 0;
        for (int i = 0; i < seeds.size(); i+=2) {
            long seed = seeds.get(i);
            long range = seeds.get(i+1);
            System.out.println("getting lowest for " + seed + " with range " + range);
            long lowestLocationForRange = getLowestLocationNumberForRange(seed, range);
            if (lowestLocation == 0) {
                lowestLocation = lowestLocationForRange;
            } else if (lowestLocationForRange < lowestLocation) {
                lowestLocation = lowestLocationForRange;
            }
        }
        return lowestLocation;
    }

    public long getLowestLocationNumberForRange(long seed, long range) {
        long lowestLocation = getLocationNumber(seed);

        //List<Long> bounds = findNextMapRangeBounds(seed, 1);
        //System.out.println("Bounds: " + bounds.get(0) + " " + bounds.get(1));
        long iterator = 1;
        int percentage = 0;
        while (iterator < range) {
            long locationNumber = getLocationNumber(seed+iterator);
            int currentP = (int) ((double) iterator / range * 100);
            if (currentP > percentage) {
                percentage = currentP;
                System.out.println(percentage);
            }

            if (locationNumber < lowestLocation) {
                lowestLocation = locationNumber;

                System.out.println("seed+iterator: " + (seed+iterator) + " location: " + locationNumber);
            }
            iterator++;
        }
        return lowestLocation;
    }

    public long getLocationNumber(long seed) {
        for (int i = 1; i < input.size(); i++) {
            seed = findNextMap(seed, i);
        }
        return seed;
    }

    public List<Long> findNextMapRangeBounds(long previous, int nextBlock) {
        List<String> block = input.get(nextBlock).subList(1,input.get(nextBlock).size());

        long lowerBound = 0;
        long upperBound = Long.MAX_VALUE;

        for (String mapping : block) {
            List<Long> values = extractFields(mapping);
            long sourceRangeStart = values.get(1);
            long rangeLength = values.get(2);

            if (previous > sourceRangeStart && sourceRangeStart > lowerBound) {
                lowerBound = sourceRangeStart;
            }

            if (previous < sourceRangeStart+rangeLength && sourceRangeStart < upperBound) {
                upperBound = sourceRangeStart+rangeLength;
            }
        }

        return Arrays.asList(lowerBound, upperBound);
    }

    public long findNextMap(long previous, int nextBlock) {

        List<String> block = input.get(nextBlock).subList(1,input.get(nextBlock).size());

        for (String mapping : block) {
            List<Long> values = extractFields(mapping);
            long destinationRangeStart = values.get(0);
            long sourceRangeStart = values.get(1);
            long rangeLength = values.get(2);

            if (sourceRangeStart <= previous && previous <= (sourceRangeStart + rangeLength)) {
                return destinationRangeStart + (previous - sourceRangeStart);
            }
        }


        return previous;
    }

    public List<Long> extractSeeds() {
        List<String> seeds = Arrays.asList(input.get(0).get(0).split(": ")[1].split(" "));
        return seeds.stream()
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private List<Long> extractFields(String mapping) {
        return Arrays.stream(mapping.split(" ")).map(Long::parseLong).collect(Collectors.toList());
    }
}
