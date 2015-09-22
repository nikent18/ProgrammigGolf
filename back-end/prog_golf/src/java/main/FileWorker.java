package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Class for working with program file
 */
public class FileWorker {
    
    private String fileName;
    private String language;
    
    /**
     * Constructor of the class FileWorker (create file with code)
     * @param code contents of the file
     * @param lang programming language
     * @param id_user user identifier
     * @param id_task task identifier
     */
    public FileWorker(String code, String lang, String id_user, String id_task) {
        
        // save programming language
        language = lang;
        
        // create file name
        
        String filepath = "C:/";
        fileName = filepath + id_user + "_" + id_task + this.fileExtansion();
        
        // create file
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        // write code in file
        try (PrintWriter pw = new PrintWriter(file.getAbsoluteFile())) {
            pw.print(code);
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File " + fileName + " is not found.");
        }
    }
    
    /**
     * Function for get a name of the file
     * @return file name (with path)
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Function for determine the file extension by programming language
     * @return file extension
     */
    private String fileExtansion() {
        switch (language) {
            case "C" : return ".c";
            case "C++" : return ".cpp";
            case "Java" : return ".java";
            case "Python" : return ".py";
        }
        return "";
    }
    
    /**
     * Function for compile program file
     * @return log of compiler / interpreter
     */
    public String compileFile() {
        
        // log of compiler / interpreter
        String output = new String();
        
        // file name of compiler (with path)
        String compiler = this.compPath();
        
        // compile / interpret
        switch(language) {
            case "C" : case "C++" :
                String execFile = "C:/main.exe";
                String command = compiler + " " + fileName + " " + "-o" + execFile;
                output = execCommand(command);
                break;
        }
        
        return output;
    }
    
    /**
     * Function for execute task in command line
     * @param command command to execute
     * @return log of the running process
     */
    private String execCommand(String command) {
        
        // log of compiler / interpreter
        StringBuilder output = new StringBuilder();

        try {
            // compiling
            Process p = Runtime.getRuntime().exec(command);
            
            // wait for completion of the process
            p.waitFor();
            
            // output stream of compiler
            BufferedReader log;
            log = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            
            // error stream of compiler
            BufferedReader errLog; 
            errLog = new BufferedReader(
                    new InputStreamReader(p.getErrorStream(), "Cp866"));
            
            // print output log
            String line;
            while ((line = log.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            // print error log
            while ((line = errLog.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            //close streams
            log.close();
            errLog.close();
            
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        return output.toString();
    }
    
    /**
     * Function for determine the path to compiler by programming language
     * @return compiler's name (with path)
     */
    private String compPath() {
        switch (language) {
            case "C" : case "C++" : return "C:/MinGW/bin/gcc.exe";
            case "Java" : return "";
            case "Python" : return "";
        }
        return "";
    }
    
}
