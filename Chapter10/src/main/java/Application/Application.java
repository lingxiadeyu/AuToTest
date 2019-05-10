package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.course")
//告诉springBoot Application启动类去找那个com.course包下的controller类
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
