package main;
import java.lang.String;
public interface LaunchProg {
    //думаю здесь должен быть общий интерфейс, описывающий методы, для компиляции или интерпретации полученных программ
    //все методы пока условны, нужно обдумать

    //создание файла
    public void createFile(String fileName);
    //запись в файл
    public String codeToFile(String code, String lang, String id_user, String id_task);
    //расширение файла
    public String fileExtansion(String lang);
    //компиляция
    public void compile(String fileName);
}
