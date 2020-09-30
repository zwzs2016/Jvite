package zwzs2016.com.github.AppService.Impl;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import zwzs2016.com.github.AppService.ApplicationymlconfigService;

import java.io.*;

@Service
public class ApplicationymlconfigServiceImpl implements ApplicationymlconfigService {
    @Override
    public String jsonfile() {
        String dump="";
        Object json=new Object();
        Yaml yaml=new Yaml();
        JSONObject jsonObject=new JSONObject();
        try {
            File file = new File("src/main/resources/application.yml");
            if (file==null){
            }else {
                BufferedReader reader=new BufferedReader(new FileReader(file));
                String str;
                String docments="";
                while ((str=reader.readLine())!=null){
                    docments+=str+'\n';
                }
                dump= JSONObject.toJSONString(yaml.load(docments));
                FileWriter fileWriter=new FileWriter("src/main/resources/static/json/application.json");
                fileWriter.write(dump);
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return dump;
    }

    public ApplicationymlconfigServiceImpl() {
    }
    @Test
    @Override
    public void setymlinfo(String ymldata) {
        Yaml yaml = new Yaml();
        Object load = yaml.load(ymldata);
//        System.out.println(yaml.dump(load));
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter("src/main/resources/application.yml");
            fileWriter.write(yaml.dump(load));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void json(){
        Yaml yaml=new Yaml();
        try {
            File file = new File("src/main/resources/application.yml");
            if (file==null){
            }else {
                BufferedReader reader=new BufferedReader(new FileReader(file));
                String str;
                String docments="";
                while ((str=reader.readLine())!=null){
                    docments+=str+'\n';
                }
                yaml.dump(yaml.load(docments));
                System.out.println(JSONObject.toJSONString(yaml.load(docments)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
