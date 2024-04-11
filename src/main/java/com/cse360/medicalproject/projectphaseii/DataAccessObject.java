package com.cse360.medicalproject.projectphaseii;
import java.io.File;
import java.io.IOException;

public class DataAccessObject {
    private final String dataFolderPath;

    public DataAccessObject(String dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
        ensureDataFolderExists();
    }

    private void ensureDataFolderExists() {
        File dataFolder = new File(dataFolderPath);
        if (!dataFolder.exists()) {
            boolean wasCreated = dataFolder.mkdir();
            if (!wasCreated) {
                throw new RuntimeException("Failed to create data folder at: " + dataFolderPath);
            }
        }
    }

    public File getFile(String fileName) {
        return new File(dataFolderPath + File.separator + fileName);
    }

    // Include other file operations here (read, write, etc.)

}

