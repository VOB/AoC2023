import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
}
