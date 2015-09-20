package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
//находится в работе
public class CPPCompilation {
    public void compilation() throws IOException {

       CPPCompilation obj = new CPPCompilation();

        //работает вызов help
        String command = "cmd /c C:/MinGW/bin/g++.exe --help";

        //файл не создается, но ошибок никаких нет
        // String command = "cmd /c C:/MinGW/bin/g++.exe D:/tests/main.cpp -o D:/o.exe ";
        String output = obj.executeCommand(command);

        System.out.println(output);
    }
    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream(),"Cp866"));
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
            output.append(r.readLine()+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}