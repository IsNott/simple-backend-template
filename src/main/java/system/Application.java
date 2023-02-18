package system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Nott
 * @Date 2023/2/16
 */

@SpringBootApplication
@MapperScan(basePackages = {"system.*.mapper.*"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
