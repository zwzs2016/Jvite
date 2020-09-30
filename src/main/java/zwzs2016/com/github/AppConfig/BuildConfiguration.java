package zwzs2016.com.github.AppConfig;


import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;
import zwzs2016.com.github.AppAnnotations.JviteAfterType;
import zwzs2016.com.github.AppAnnotations.JviteConfig;
import zwzs2016.com.github.AppAnnotations.JviteJsonFileName;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BuildConfiguration {
    public static String filename="src/main/resources/application.yml";

    public BuildConfiguration(ProjectConfiguration pro) {
        JviteConfig jviteConfig = pro.getClass().getAnnotation(JviteConfig.class);
        Field[] fields = pro.getClass().getDeclaredFields();
        JSONObject jsonObject=new JSONObject();
        Yaml yaml=new Yaml();
        for (Field f:fields){
            if (f.isAnnotationPresent(JviteJsonFileName.class)){
                JviteJsonFileName fAnnotation = f.getAnnotation(JviteJsonFileName.class);
                f.setAccessible(true);
                try {
                    f.set(pro,fAnnotation.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (f.isAnnotationPresent(JviteAfterType.class)){
                JviteAfterType jviteAfterType=f.getAnnotation(JviteAfterType.class);
                String setmethod="set"+jviteAfterType.value().substring(0,1).toUpperCase()+jviteAfterType.value().substring(1);
                String getmenthod="get"+jviteAfterType.value().substring(0,1).toUpperCase()+jviteAfterType.value().substring(1);
                f.setAccessible(true);
                BufferedReader jsonwirte=null;
                try {
                    Method methodset = pro.getClass().getMethod(setmethod);
                    Method methodget = pro.getClass().getMethod(getmenthod);
                    methodset.invoke(pro);
                    Object invoke = methodget.invoke(pro);
                    List<String> filenamelist=(List<String>)invoke;
                    JSONObject Json=new JSONObject();
                    int time=0;
                    String jsoncontext="";
                    for (String filename:filenamelist){
                        String jsonstr="";
                        String filepath=jviteConfig.value()+filename+".json";
                        jsonwirte=new BufferedReader(new FileReader(new File(filepath)));
                        String str;
                        while ((str=jsonwirte.readLine())!=null){
                            jsonstr+=str;
                        }
                        if (time<filenamelist.size()-1){
                            jsoncontext+=jsonstr.substring(1,jsonstr.length()-1)+",";
                            time++;
                        }else {
                            jsoncontext+=jsonstr.substring(1,jsonstr.length()-1);
                        }
                    }
                    jsoncontext="{"+jsoncontext+"}";
                    Json=JSONObject.parseObject(jsoncontext);
                    jsonObject.put(jviteAfterType.value(),Json);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        jsonwirte.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String ymldata=yaml.dump(yaml.load(jsonObject.toString()));
        String filename=this.filename;
        try {
            FileWriter ymlwirte=new FileWriter(filename);
            ymlwirte.write(ymldata);
            ymlwirte.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
