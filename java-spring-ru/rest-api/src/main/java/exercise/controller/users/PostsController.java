package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
class PostsController {

    private List<Post> posts = new ArrayList<>();

    @GetMapping("/users/{id}/posts")
    public List<Post> getPosts(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getUserId() == Integer.parseInt(id))
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable String id, @RequestBody Post post) {
        var newPost = new Post();
        newPost.setUserId(Integer.parseInt(id));
        newPost.setSlug(post.getSlug());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        posts.add(newPost);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newPost);
    }
}
// END
