import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Path sourse = Paths.get("D://Games/SaveGames/saves.zip");
        Path target = Paths.get("D://Games/SaveGames/");

        openZip(sourse, target);

        gameProgress("D://Games/SaveGames/save1.dat");
        gameProgress("D://Games/SaveGames/save2.dat");
        gameProgress("D://Games/SaveGames/save3.dat");
    }

    public static void openZip(Path source, Path target) throws IOException {

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                boolean isDirectory = false;
                if (zipEntry.getName().endsWith(File.separator)) {
                    isDirectory = true;
                }
                Path newPath = zipSlipProtect(zipEntry, target);

                if (isDirectory) {
                    Files.createDirectories(newPath);
                } else {
                    if (newPath.getParent() != null) {
                        Files.createDirectories((newPath.getParent()));
                    }
                }
                Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                zipEntry = zis.getNextEntry();
            }
            zipEntry = zis.getNextEntry();
            zis.closeEntry();
        }


    }

    private static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir) throws IOException {
        Path targetDirResolved = targetDir.resolve(zipEntry.getName());

        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }

        return normalizePath;
    }

    public static void gameProgress(String filePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (IOException e) {
            System.out.println("Сбой десериализации");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gameProgress);
    }
}
