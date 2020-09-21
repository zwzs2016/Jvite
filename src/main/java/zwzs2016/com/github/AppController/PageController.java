package zwzs2016.com.github.AppController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zwzs2016.com.github.AppService.ApplicationymlconfigService;

@Controller
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
}
