import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    public List<String> lines(String textfile) {
        Path filePath = Path.of(textfile);
        List<String> results = new ArrayList<>();
        try {
            results = Files.readAllLines(filePath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return results;
    }

    public char[][] linesAsCharArray(String textfile) {
        List<String> lines = lines(textfile);
        char[][] input = new char[lines.get(0).length()][lines.size()];
        for (int i=0; i<lines.size(); i++) {
            input[i] = lines.get(i).toCharArray();
        }
        return input;
    }

    public List<List<String>> blocks(String textfile) {
        Path filePath = Path.of(textfile);
        String results = "";
        try {
            results = Files.readString(filePath);
        }catch (IOException e){
            e.printStackTrace();
        }

        String[] resultList = results.split("\r\n\r\n");
        List<List<String>> blocks = new ArrayList<>();

        for (String block : resultList ) {
            blocks.add(Arrays.asList(block.split("\r\n")));
        }

        return blocks;
    }



}
