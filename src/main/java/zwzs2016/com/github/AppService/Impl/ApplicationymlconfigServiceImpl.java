package zwzs2016.com.github.AppService.Impl;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import zwzs2016.com.github.AppService.ApplicationymlconfigService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class ApplicationymlconfigServiceImpl implements ApplicationymlconfigService {
    @Override
    public String jsonfile() {
        String dump="";
        Object json=new Object();
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
//                json = JSONObject.toJSON(docments);
                dump= JSONObject.toJSONString(yaml.load(docments));
//                dump = yaml.dump(yaml.load(docments));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return dump;
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
//                System.out.println(new JSONObject().parse(resultString).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
