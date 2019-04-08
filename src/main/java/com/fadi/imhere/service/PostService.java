package com.fadi.imhere.service;

import com.fadi.imhere.Utils.DtoUtils;
import com.fadi.imhere.dtos.PostDto;
import com.fadi.imhere.model.Post;

import com.fadi.imhere.model.User;
import com.fadi.imhere.repository.PostRepository;
import com.fadi.imhere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

   @Transactional
    public PostDto createPost(PostDto postDto) {
        PostDto postToReturn = null;
        Post post = DtoUtils.convertPostToEntity(postDto);
        UUID userId = postDto.getUser().getId();
        Optional<User> user = userRepository.findById(userId);
        if (user != null){
            post.setUser(user.get());
            Post PostSaved = postRepository.save(post);
            postToReturn = DtoUtils.convertPostToDto(PostSaved,userId);
        }
        return postToReturn;
    }

    @Transactional(readOnly = true)
    public List<PostDto> findAll(UUID userId) {
        return DtoUtils.makeList(postRepository.findAll())
                .stream()
                .map(p -> DtoUtils.convertPostToDto(p, userId))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostDto getPostById(UUID postId, UUID userId) {
        //Post post = postRepository.findById(postId);
        PostDto postDto = null;

/*
        if (post != null) {
            postDto = (PostDto) DtoUtils.convertPostToDto(post, userId);
        }
        else {
            System.out.println("No post found in data base with the id '{}'"+ postId);
        }*/
        return postDto;
    }
}
