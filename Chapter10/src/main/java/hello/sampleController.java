package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class sampleController {
    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "hello Spring Boot";
    }
//直接右键执行启动服务，然后在浏览器中输入localhost:8080就能返回“hello Spring Boot”
    public static void main(String[] args) {
        SpringApplication.run(sampleController.class,args);
    }

}
