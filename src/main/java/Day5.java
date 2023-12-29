import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    TextParser textParser = new TextParser();
    List<List<String>> input;

    List<Long> seeds;
    List<List<Mapping>> blocks;


    public Day5() {
        input = textParser.blocks("src/main/Day5");
        seeds = extractSeeds();
        blocks = extractMappings();
    }

    public Day5(String input) {
        this.input = textParser.blocks(input);
        seeds = extractSeeds();
        blocks = extractMappings();
    }

    public long part1() {
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
        List<Long> seedsWithRanges = extractSeeds();
        List<SeedRange> seedRanges = createSeedRanges(seedsWithRanges);

        long locationIterator = 0;
        while (!seedMappingExistsForLocation(locationIterator, seedRanges)) {
            locationIterator++;
        }
        return locationIterator;
    }

    public boolean seedMappingExistsForLocation(long location, List<SeedRange> seedRanges) {

        for (int i = blocks.size()-1; i>=0; i--) {
            location = findPreviousMap(location, i);
        }

        for (SeedRange seedRange : seedRanges) {
            if (location >= seedRange.start && location <= seedRange.end) {
                return true;
            }
        }
        return false;
    }

    public long getLocationNumber(long seed) {
        for (int i = 0; i < blocks.size(); i++) {
            seed = findNextMap(seed, i);
        }
        return seed;
    }

    public long findNextMap(long previous, int blockIndex) {

        List<Mapping> block = blocks.get(blockIndex);

        for (Mapping mapping : block) {
            if (mapping.sourceRangeStart <= previous && previous < (mapping.sourceRangeStart + mapping.rangeLength)) {
                return mapping.destinationRangeStart + (previous - mapping.sourceRangeStart);
            }
        }

        return previous;
    }

    public long findPreviousMap(long previous, int blockIndex) {

        List<Mapping> block = blocks.get(blockIndex);

        for (Mapping mapping : block) {
            if (mapping.destinationRangeStart <= previous && previous < (mapping.destinationRangeStart + mapping.rangeLength)) {
                return mapping.sourceRangeStart + (previous - mapping.destinationRangeStart);
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

    private List<List<Mapping>> extractMappings() {
        List<List<Mapping>> mappingsList = new ArrayList<>();

        for (int i=1; i<input.size(); i++) {
            List<Mapping> mappings = new ArrayList<>();
            List<String> block = input.get(i).subList(1,input.get(i).size());

            for (String mapping : block) {
                mappings.add(extractMapping(mapping));
            }

            mappingsList.add(mappings);
        }

        return mappingsList;
    }

    private Mapping extractMapping(String mapping) {
        List<Long> mappingsAsLong = Arrays.stream(mapping.split(" ")).map(Long::parseLong).toList();
        return new Mapping(mappingsAsLong.get(0), mappingsAsLong.get(1), mappingsAsLong.get(2));
    }

    private List<SeedRange> createSeedRanges(List<Long> seedsWithRanges) {
        List<SeedRange> seedRanges = new ArrayList<>();

        for (int i=0; i < seedsWithRanges.size(); i+=2) {
            seedRanges.add(new SeedRange(seedsWithRanges.get(i), seedsWithRanges.get(i) + seedsWithRanges.get(i+1)));
        }

        return seedRanges;
    }
    private static class SeedRange {

        long start;
        long end;

        public SeedRange(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Mapping {
        long destinationRangeStart;
        long sourceRangeStart;
        long rangeLength;

        public Mapping(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
            this.destinationRangeStart = destinationRangeStart;
            this.sourceRangeStart = sourceRangeStart;
            this.rangeLength = rangeLength;
        }
    }
}
