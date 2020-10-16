package zwzs2016.com.github.AppConfig;


import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;
import zwzs2016.com.github.AppAnnotations.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

public final class BuildConfiguration {
    public static String filename="src/main/resources/application.yml";
    public static String filename_pps="src/main/resources/application.properties";
    private static Long lastTime;
    private Logger log = Logger.getLogger(BuildConfiguration.class.getPackage().getName()+".BuildConfiguration");
    public BuildConfiguration(ProjectConfiguration pro) {
        System.out.println("json");
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

        public BuildConfiguration(ProjectConfiguration_yml pro) {
            String filename=this.filename;
            File file=new File(filename);
            JviteConfig jviteConfig = pro.getClass().getAnnotation(JviteConfig.class);
            Field[] fields = pro.getClass().getDeclaredFields();
            Yaml yaml=new Yaml();
            StringBuilder ymlstr=new StringBuilder();
            String str="";
            for (Field f:fields){
                if (f.isAnnotationPresent(JviteYmlFileName.class)){
                    JviteYmlFileName fAnnotation = f.getAnnotation(JviteYmlFileName.class);
                    f.setAccessible(true);
                    try {
                        f.set(pro,fAnnotation.value());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    String ymlname=jviteConfig.value()+fAnnotation.value()+".yml";
                    BufferedReader ymlread= null;
                    try {
                        ymlread = new BufferedReader(new FileReader(new File(ymlname)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    while (true){
                        try {
                            if ((str=ymlread.readLine())==null) break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ymlstr.append(str+"\n");
                    }
                }
            }
            String ymlfilename=filename;
            FileWriter ymlwrite=null;
            try {
                ymlwrite=new FileWriter(ymlfilename);
                yaml.dump(yaml.load(String.valueOf(ymlstr)),ymlwrite);
                ymlwrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("Configuration File Created Successfully");
        }
        public BuildConfiguration(ProjectConfiguration_properties pro){
            String filename=this.filename_pps;
            File file=new File(filename);
            JviteConfig jviteConfig = pro.getClass().getAnnotation(JviteConfig.class);
            Field[] fields = pro.getClass().getDeclaredFields();
            Properties properties=new Properties();
            StringBuilder ppsdata=new StringBuilder();
            String str="";
            for (Field f:fields){
                if(f.isAnnotationPresent(JvitePropertiesFileName.class)){

                    JvitePropertiesFileName fannotation = f.getAnnotation(JvitePropertiesFileName.class);
                    f.setAccessible(true);
                    try {
                        f.set(pro,fannotation.value());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    String ppsname=jviteConfig.value()+fannotation.value()+".properties";
                    BufferedReader ppsreader=null;
                    try {
                        ppsreader = new BufferedReader(new FileReader(new File(ppsname)));
                        while ((str=ppsreader.readLine())!=null){
                            ppsdata.append(str+"\n");
                        }
                        ppsreader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            FileWriter ppswirte=null;
            try {
                ppswirte=new FileWriter(new File(filename));
                ppswirte.write(String.valueOf(ppsdata));
                ppswirte.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
