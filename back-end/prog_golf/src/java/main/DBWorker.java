package main;

/**
 * Class for working with database.
 */
public class DBWorker {
    
    /**
     * Constructor of the class DBWorker (connect to database).
     */
    public DBWorker() {
        
        // connect to DB ...
        
    }
    
    /**
     * Function to get the path to compiler by programming language.
     * @param lang programming language
     * @return compiler's name (with path)
     */
    public String getCompilerPath(String lang) {
        
        // SELECT ...
        
        // temporary code:
        switch (lang) {
            case "C" : case "C++" :
                return "C:/MinGW/bin/gcc.exe";
            case "Java" :
                return "C:/Program Files/Java/jdk1.8.0_60/bin/javac.exe";
        }
        return "";
    }
    
    /**
     * Function to get extension of compiled file by programming language.
     * @param lang programming language
     * @return extension for compiled file
     */
    public String getCompFileExtension(String lang) {
        
        // SELECT ...
        
        // temporary code:
        switch (lang) {
            case "C" : return ".c";
            case "C++" : return ".cpp";
            case "Java" : return ".java";
        }
        return "";
    }
    
    /**
     * Function to get options of compiler by programming language.
     * @param lang programming language
     * @return options of compiler
     */
    public String getCompilerOptions(String lang) {
        
        // SELECT ...
        
        // temporary code:
        switch (lang) {
            case "C" : case "C++" :
                return "-o";
            case "Java" :
                return "";
        }
        return "";
    }
    
    /**
     * Function to get name of executed file by programming language.
     * @param lang programming language
     * @param filePath path for file storage
     * @param fileName short name of program file (without path)
     * @return extension for executed file
     */
    public String getExecFileName(String lang, String filePath, String fileName) {
        
        // SELECT ...
        
        // temporary code:
        switch (lang) {
            case "C" : case "C++" :
                return filePath + fileName + ".exe";
            case "Java" :
                return "";
        }
        return "";
    }
    
    /**
     * Function to get input test values for the task.
     * @param task_id identifier of the task
     * @return String array of input test values
     */
    public String[] getInputTestValues(String task_id) {
        
        // SELECT ...
        
        // temporary code:
        String[] str = {"", ""};
        return str;
    }
    
    /**
     * Function to get output test values for the task.
     * @param task_id
     * @return String array of output test values
     */
    public String[] getOutputTestValues(String task_id) {
        
        // SELECT ...
        
        // temporary code:
        String[] str = {"", ""};
        return str;
    }
    
}
