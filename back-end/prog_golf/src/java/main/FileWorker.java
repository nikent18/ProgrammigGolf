package main;

import java.io.BufferedReader;
import java.io.File;
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
    
    // object of DBWorker class (for get some info about compilers, etc.)
    private final DBWorker db;
    
    /**
     * Constructor of the class FileWorker (create file with code).
     * Name of created file consisting of user ID and task ID.
     * @param code contents of the file (class name is change for java file)
     * @param lang programming language
     * @param id_user user identifier
     * @param id_task task identifier
     * @throws java.io.IOException if can't create/find program file
     */
    public FileWorker(String code, String lang, String id_user, String id_task) 
            throws IOException {
        
        // connect with database
        db = new DBWorker();
                
        // path for file storage
        filePath = "C:/";
        
        // programming language
        language = lang;
        
        // create file name
        shortFileName = id_user + "_" + id_task;
        fullFileName = filePath + id_user + "_" + id_task + 
                db.getCompFileExtension(lang);
        
        // change class name for java file
        if(language.equals("Java"))
            code = changeClassName(code);
        
        // create file
        File file = new File(fullFileName);
        file.createNewFile();
        
        // write code in file
        try (PrintWriter pw = new PrintWriter(file.getAbsoluteFile())) {
            pw.print(code);
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
     * Function for compile program file.
     * @return log of compiler / interpreter
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public String compileFile() throws IOException, InterruptedException {
        
        // executable command
        String command = db.getCompilerPath(language) + " " +
                fullFileName + " " + 
                db.getCompilerOptions(language) + " " +
                db.getExecFileName(language, filePath, shortFileName);
        
        // compile / interpret and return log
        return execCommand(command);
    }
    
    /**
     * Function for execute task in command line.
     * @param command command to execute
     * @return log of the running process
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    private String execCommand(String command) throws 
            IOException, InterruptedException {
        
        // log of compiler / interpreter
        StringBuilder output = new StringBuilder();

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

        return output.toString();
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
        
        // checking the number of words in code
        // should be made on the client side !?
        
        // search class name
        while(!st.nextToken().equals("class")) ;
        
        // get class name
        String className = st.nextToken();
        System.out.println(className);
        
        // change class name with short file name
        return code.replace(className, shortFileName);
    }
    
}