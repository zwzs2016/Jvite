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
import java.util.logging.Logger;

public final class BuildConfiguration {
    public static String filename="src/main/resources/application.yml";
    private static Long lastTime;
    private Logger log = Logger.getLogger(BuildConfiguration.class.getPackage().getName()+".BuildConfiguration");
    public BuildConfiguration(ProjectConfiguration pro) {
        String filename=this.filename;
        File file=new File(filename);
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
                    StringBuilder jsoncontext=new StringBuilder();
                    for (String jsonfilename:filenamelist){
                        StringBuilder jsonstr=new StringBuilder();
                        String filepath=jviteConfig.value()+jsonfilename+".json";
                        jsonwirte=new BufferedReader(new FileReader(new File(filepath)));
                        String str;
                        while ((str=jsonwirte.readLine())!=null){
                            jsonstr.append(str);
                        }
                        if (time<filenamelist.size()-1){
                            jsoncontext.append(jsonstr.substring(1,jsonstr.length()-1)+",");
                            time++;
                        }else {
                            jsoncontext.append(jsonstr.substring(1,jsonstr.length()-1));
                        }
                    }
                    jsoncontext.insert(0,"{").append("}");
                    Json=JSONObject.parseObject(String.valueOf(jsoncontext));
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
        try {
            FileWriter ymlwirte=new FileWriter(filename);
            ymlwirte.write(ymldata);
            ymlwirte.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Configuration File Created Successfully");
        }
}
