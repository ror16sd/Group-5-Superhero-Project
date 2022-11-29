package superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import superhero.model.Super;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SuperHeroApplication {
    public static void main(String[] args) {

        SpringApplication.run(SuperHeroApplication.class, args);
    }

}
