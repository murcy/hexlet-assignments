package exercise.daytime;

import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        var message = "Night Bean is initialized!";
        System.out.println(message);
    }
    // END
}
