package com.myblog.blogapp.Service;

import com.myblog.blogapp.Payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDto);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(long id);
    PostDTO updatePost(PostDTO postDTO, long id);
    void deletePost(long id);
}
