import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Create a specific directory
        String dir = "dossier_specifique";
        new File(dir).mkdirs();

        Random rand = new Random();

        for (int i = 0; i < 50000; i++) {
            int randomNum = rand.nextInt();
            String fileName = dir + "/fichier_" + randomNum;

            try {
                new File(fileName).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Delete all files in the specific directory
        try {
            Files.walk(Paths.get(dir))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(file -> {
                        boolean isDeleted = file.delete();
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Temps d'execution: " + executionTime + " millisecondes");
    }
}