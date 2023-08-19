import java.io.File;
import java.io.*;
import java.nio.file.*;
import java.util.logging.*;
//todo - in this class create two methods that can handle file copying
// 1.first method must include src file and from it your method will have to
// create another duplicate file. Then name of duplicate file should be like
// notes.txt => notes(1).txt
// 2.second method must include as an input two parameters , scr file and
// destination file. This method will reads from src file and writes to
// destination file.
// 3.For the class involve logger to log method operation details.
// When your methods throws an exception log error message to the console
// When copy file successfully finishes log info about it.
public class Streams {
    //File file = new File("src","notes.txt/notes(1).txt");
    private static final Logger logger = Logger.getLogger(FileHandler.class.getName());
    public void duplicateFile(String srcFile) {
        try {
            Path srcPath = Paths.get(srcFile);
            String newFileName = getNewFileName(srcFile);
            Path destPath = Paths.get(newFileName);
            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            logger.log(Level.INFO, "File copied successfully from " + srcFile + " to " + newFileName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred during file copying", e);
        }
    }
    public void copyFile(String srcFile, String destFile) {
        try {
            Path srcPath = Paths.get(srcFile);
            Path destPath = Paths.get(destFile);
            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            logger.log(Level.INFO, "File copied successfully from " + srcFile + " to " + destFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred during file copying", e);
        }
    }
    private String getNewFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf('.');
        if (dotIndex > 0) {
            String fileName = oldFileName.substring(0, dotIndex);
            String extension = oldFileName.substring(dotIndex);
            return fileName + "(1)" + extension;
        } else {
            return oldFileName + "(1)";
        }
    }
}
