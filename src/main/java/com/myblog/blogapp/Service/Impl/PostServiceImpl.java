package com.myblog.blogapp.Service.Impl;

import com.myblog.blogapp.Entities.Post;
import com.myblog.blogapp.Exceptions.ResourceNotFoundException;
import com.myblog.blogapp.Payload.PostDTO;
import com.myblog.blogapp.Repositories.PostRepository;
import com.myblog.blogapp.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


	@Autowired
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }
    @Override
    public PostDTO createPost(PostDTO postDto) {
        Post post = mapToEntity(postDto);
        Post postEntity = postRepo.save(post);
        PostDTO dto = mapToDto(postEntity);
        return dto;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepo.findAll();
       return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        PostDTO postDTO = mapToDto(post);
        return postDTO;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
      );
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post newPost = postRepo.save(post);
        return mapToDto(newPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        postRepo.deleteById(id);
    }

    public Post mapToEntity(PostDTO postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    public PostDTO mapToDto(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }
}
