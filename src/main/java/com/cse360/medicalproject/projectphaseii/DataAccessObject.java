package com.cse360.medicalproject.projectphaseii;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
    
    public boolean isDoctorIdValid(String doctorId) {
        File file = getFile("doctors.txt"); 
        try {
        	List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String line : lines) {
                // Extract the ID part before the comma
                String[] parts = line.split(",", 2);
                if (parts.length > 0 && parts[0].trim().equals(doctorId)) {
                    return true;  // Return true if the ID matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isPatientIdValid(String patientId) {
    	File patientFile = getFile(patientId+".txt");
        return patientFile.exists();
              
    }
    
    public String getDoctorNameById(String id) {
        DataAccessObject dao = new DataAccessObject("data");
        File doctorFile = dao.getFile("doctors.txt");
        try {
            List<String> lines = Files.readAllLines(Paths.get(doctorFile.getPath()));
            for (String line : lines) {
            	  String[] parts = line.split(":", 2); // Splitting by colon
                  String[] idName = parts[0].split(",", 2); // Splitting the first part by comma
                if (idName[0].equals(id)) {
                    return idName.length > 1 ? idName[1].trim() : ""; // Return the name if ID matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if no matching ID is found
    }
    
    public void saveHistory(String doctorId, String doctorName) {
        String dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
        File doctorFile = getFile("doctors.txt");
        
        try {
            List<String> lines = new ArrayList<>();
            boolean entryFound = false;

            if (doctorFile.exists()) {
                lines = Files.readAllLines(Paths.get(doctorFile.getPath()));
                for (int i = 0; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(":", 2);
                    String[] idName = parts[0].split(",", 2);

                    if (idName[0].trim().equals(doctorId)) {
                        // Append new date/time to the existing record, separated by a semicolon
                        lines.set(i, lines.get(i) + ";" + dateTime);
                        entryFound = true;
                        break;
                    }
                }
            }

            // Write the updated list back to the file
            Files.write(Paths.get(doctorFile.getPath()), lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  

	public String getDoctorHistoryById(String doctorId) {
		File doctorFile = getFile("doctors.txt");
        StringBuilder historyBuilder = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(Paths.get(doctorFile.getPath()));
            for (String line : lines) {
                String[] parts = line.split(":", 2);
                String[] idName = parts[0].split(",", 2);

                if (idName[0].trim().equals(doctorId) && parts.length > 1) {
                    historyBuilder.append(parts[1].replace(";", "\n"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historyBuilder.toString();
	}
	
	  public List<String> getAllPatientIds() {
	        File dataFolder = new File(dataFolderPath);
	        FilenameFilter textFilter = (dir, name) -> name.matches("\\d{8}.txt");
	        File[] files = dataFolder.listFiles(textFilter);

	        return Arrays.stream(files)
	                     .map(file -> file.getName().replace(".txt", ""))
	                     .collect(Collectors.toList());
	    }
	  
	  
	    // Method to get messages for a specific doctor-patient pair
	    public List<String> getMessagesForPatientAndDoctor(String doctorId, String patientId) {
	        List<String> messages = new ArrayList<>();
	        File messageFile = new File(dataFolderPath, doctorId + "_" + patientId + "_messages.txt");

	        if (messageFile.exists()) {
	            try {
	                List<String> allMessages = Files.readAllLines(messageFile.toPath());
	                for (String message : allMessages) {
	                    if (message.startsWith(patientId + "_" + doctorId) || message.startsWith(doctorId + "_" + patientId)) {
	                        messages.add(message);
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return messages;
	    }
	
	 public void sendMessage(String doctorId, String patientId, String message) {
		 
	        String filename = doctorId + "_" + patientId + "_messageDoctor.txt";
	        File messageFile = getFile(filename);

	        try {
	            Files.write(Paths.get(messageFile.getPath()), (message + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 



}

