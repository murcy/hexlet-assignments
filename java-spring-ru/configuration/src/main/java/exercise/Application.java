package exercise;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private UserProperties usersProp;

    @GetMapping("/admins")
    public List<String> getAdminsName() {
        var adminsEmail = usersProp.getAdmins();
        var listUsers = new ArrayList<Optional<User>>();
        adminsEmail.forEach(email -> listUsers.add(findUser(email)));

        return listUsers.stream()
                .map(user -> user.get().getName())
                .toList();
    }

    private Optional<User> findUser(String adminEmail) {
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(), adminEmail))
                .findFirst();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
