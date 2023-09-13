package STE.service.managment;

import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class InputData {

    private static final Map<String,List<String>> personInputData;
    private static final Map<String,List<String>> organizerInputData;
    private static final Map<String,List<String>> tournamentInputData;

static {
    try {
        personInputData =readInputDataFileForPerson();
        organizerInputData =readInputDataFileForOrganizer();
        tournamentInputData =readInputDataFileForTournament();

    } catch (IOException e) {
        throw new RuntimeException("Read file problem");
    }
}

    private static Map<String, List<String>> readInputDataFileForTournament() throws IOException {
        Path path = ResourceUtils.getFile("classpath:inputDataFile.md").toPath();
        List<String> inputDataForPerson = Files.readAllLines(path).stream()
                .filter(line -> line.startsWith(Categories.Entity.TOURNAMENT.toString()))
                .map(line->line.substring(line.indexOf(";")+1))
                .toList();

        return inputDataForPerson.stream()
                .collect(Collectors.groupingBy(line->line.split(",")[0].trim(),
                        Collectors.mapping(lines->lines.substring(lines.indexOf(",")+1).trim(),Collectors.toList())));

    }

    private static Map<String, List<String>> readInputDataFileForOrganizer() throws IOException {
        Path path = ResourceUtils.getFile("classpath:inputDataFile.md").toPath();
        List<String> inputDataForPerson = Files.readAllLines(path).stream()
                .filter(line -> line.startsWith(Categories.Entity.ORGANIZER.toString()))
                .map(line->line.substring(line.indexOf(";")+1))
                .map(line->line.split("//")[0].trim())
                .toList();

        return inputDataForPerson.stream()
                .collect(Collectors.groupingBy(line->line.split(",")[0].trim(),
                        Collectors.mapping(lines->lines.substring(lines.indexOf(",")+1).trim(),Collectors.toList())));

    }

    public static Map<String,List<String>> readInputDataFileForPerson() throws IOException {
        Path path = ResourceUtils.getFile("classpath:inputDataFile.md").toPath();
        List<String> inputDataForPerson = Files.readAllLines(path).stream()
                .filter(line -> line.startsWith(Categories.Entity.PERSON.toString()))
                .map(line->line.substring(line.indexOf(";")+1))
                .map(line->line.split("//")[0].trim())
                .toList();

       return inputDataForPerson.stream()
                .collect(Collectors.groupingBy(line->line.split(",")[0].trim(),
                        Collectors.mapping(lines->lines.substring(lines.indexOf(",")+1).trim(),Collectors.toList())));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(personInputData);
        System.out.print(organizerInputData);
        System.out.print(tournamentInputData);

    }

}
