package zwzs2016.com.github.AppController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zwzs2016.com.github.AppService.ApplicationymlconfigService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@CrossOrigin
public class PageController {
    @Autowired
    ApplicationymlconfigService ymlconfigService;

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
    @GetMapping("/Jvite")
    public String Jvite(){
        return "Jvite";
    }
    @GetMapping("/pomxmlconfig")
    public String pomxmlconfig(){
        return "pomxmlconfig";
    }
    @GetMapping("/applicationymlconfig")
    public String applicationymlconfig(){
        return "applicationymlconfig";
    }
    @GetMapping("/configlist")
    public String configlist(){
        return "configlist";
    }
    @CrossOrigin
    @RequestMapping("/applicationymlconfig/getymlinfo")
    @ResponseBody
    public String getymlinfo(){
        return ymlconfigService.jsonfile();
    }
    @RequestMapping("/jsontest")
    @ResponseBody
    public String jsonstr(){
        String resultString = "{\"result\":true}";
        return resultString;
    }
    @CrossOrigin
    @RequestMapping("/applicationymlconfig/setymlinfo")
    @ResponseBody
    public String setymlinfo(HttpServletRequest request){
        System.out.println(request);
        try {
            BufferedReader br=request.getReader();
            StringBuilder stringBuilder=new StringBuilder();
            String str="";
            while ((str=br.readLine())!=null){
                stringBuilder.append(str);
            }
            System.out.println(stringBuilder.toString());
            ymlconfigService.setymlinfo(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
