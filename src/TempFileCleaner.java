import java.io.File;
import java.io.IOException;

public class TempFileCleaner {
    
    public static void main(String[] args) {
        System.out.println("Hello");
        String tempDir = System.getProperty("java.io.tmpdir");
        System.out.println("Temporary Directory: " + tempDir);

        File directory = new File(tempDir);

        if (!directory.exists()) {
            System.out.println("Temporary directory does not exist.");
            return;
        }

        deleteTempFiles(directory);
    }

    private static void deleteTempFiles(File directory) {
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isTempFile(file)) {
                    try {
                        if (file.delete()) {
                            System.out.println("Deleted: " + file.getAbsolutePath());
                        } else {
                            System.out.println("Failed to delete: " + file.getAbsolutePath());
                        }
                    } catch (SecurityException e) {
                        System.out.println("Permission denied: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private static boolean isTempFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".tmp") || fileName.endsWith(".temp") || fileName.startsWith("temp");
    }
}
