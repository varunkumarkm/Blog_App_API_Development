package com.myblog.blogapp.Controller;

import com.myblog.blogapp.Payload.PostDTO;
import com.myblog.blogapp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
     return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }
    //localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") long id){
        PostDTO dto = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //localhost:8080/api/posts/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
    }
}
