package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Class for working with program file.
 */
public class FileWorker {
    
    // path for file storage
    private final String filePath;
    
    // short name of the file (without path and extension)
    private final String shortFileName;
    
    // full name of the file (with path and extension)
    private final String fullFileName;
    
    // programming language
    private final String language;
    
    /**
     * Constructor of the class FileWorker (create file with code).
     * Name of created file consisting of user ID and task ID.
     * @param code contents of the file (class name is change for java file)
     * @param lang programming language
     * @param id_user user identifier
     * @param id_task task identifier
     */
    public FileWorker(String code, final String lang, 
            final String id_user, final String id_task) {
        
        // path for file storage
        this.filePath = "C:/";
        
        // programming language
        language = lang;
        
        // create file name
        shortFileName = id_user + "_" + id_task;
        fullFileName = filePath + id_user + "_" + id_task + this.fileExtansion();
        
        // change class name for java file
        if(language.equals("Java"))
            code = changeClassName(code);
        
        // create file
        File file = new File(fullFileName);
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
            System.out.println("Error: File " + fullFileName + " is not found.");
        }
    }
    
    /**
     * Function for get full name of the file (with path and extension).
     * @return full file name (with path and extension)
     */
    public String getFullFileName() {
        return fullFileName;
    }
    
    /**
     * Function for get short name of the file (without path and extension).
     * @return short file name (without path and extension)
     */
    public String getShortFileName() {
        return shortFileName;
    }
    
    /**
     * Function for determine the file extension by programming language.
     * @return file extension
     */
    private String fileExtansion() {
        switch (language) {
            case "C" : return ".c";
            case "C++" : return ".cpp";
            case "Java" : return ".java";
        }
        return "";
    }
    
    /**
     * Function for compile program file.
     * @return log of compiler / interpreter
     */
    public String compileFile() {
        
        // log of compiler / interpreter
        String output = new String();
        
        // name of compiler / interpreter (with path)
        String compiler = this.compPath();
        
        // name of executable file
        String execFile;
        
        // executable command
        String command;
        
        // compile / interpret
        switch(language) {
            case "C" : case "C++" :
                execFile = filePath + shortFileName + ".exe";
                command = compiler + " " + fullFileName + " " + "-o" + execFile;
                output = execCommand(command);
                break;
            case "Java" :
                command = compiler + " " + fullFileName;
                output = execCommand(command);
                break;
        }
        
        return output;
    }
    
    /**
     * Function for execute task in command line.
     * @param command command to execute
     * @return log of the running process
     */
    private String execCommand(final String command) {
        
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
     * Function for determine the path to compiler by programming language.
     * @return compiler's name (with path)
     */
    private String compPath() {
        switch (language) {
            case "C" : case "C++" :
                return "C:/MinGW/bin/gcc.exe";
            case "Java" :
                return "C:/Program Files/Java/jdk1.8.0_60/bin/javac.exe";
        }
        return "";
    }
    
    /**
     * Function for changing name of the java class.
     * @param code java programming code
     * @return changed java programming code
     */
    @SuppressWarnings("empty-statement")    // empty while statement
    private String changeClassName(String code) {
        
        // object for parsing of string
        StringTokenizer st = new StringTokenizer(code);
        
        // search class name
        while(!st.nextToken().equals("class")) ;
        
        // get class name
        String className = st.nextToken();
        System.out.println(className);
        
        // change class name with short file name
        return code.replace(className, shortFileName);
        
    }
    
}
