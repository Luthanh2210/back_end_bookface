package com.example.thetitans.bookface.controller.post;

import com.example.thetitans.bookface.dto.PostRequest;
import com.example.thetitans.bookface.dto.PostResponse;
import com.example.thetitans.bookface.service.user.impl.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostById(id));
    }

    @GetMapping("by-user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable Long userId) {
        return status(HttpStatus.OK).body(postService.getPostsByUserId(userId));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updatePost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}