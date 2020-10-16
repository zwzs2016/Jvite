import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileRead {
    @Test
    public void fileinput(){
        String path="src/test/resources/static/test.txt";
        byte[] bytes=new byte[1024];
        int num;
        String str;
        try {
            FileInputStream javafile=new FileInputStream(path);
            while ((num=javafile.read(bytes))!=-1){
                System.out.println(new String(bytes));
            }
            javafile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
