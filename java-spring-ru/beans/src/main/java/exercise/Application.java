package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;


// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @RequestScope
    @Bean
    public Daytime getDaytime() {
        var am6 = LocalDateTime.now().withHour(6);
        var pm22 = LocalDateTime.now().withHour(22);

        if (LocalDateTime.now().isAfter(am6) && LocalDateTime.now().isBefore(pm22)) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
