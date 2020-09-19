package zwzs2016.com.github.AppController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
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
}
