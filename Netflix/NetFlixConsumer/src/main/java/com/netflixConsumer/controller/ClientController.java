package com.netflixConsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflixConsumer.model.Post;
import com.netflixConsumer.service.ClientListner;

@RestController
@RequestMapping("/api/netflixMsg")
public class ClientController {
	@Autowired
	private ClientListner list;
	
	@GetMapping("/posts")
	public List<Post> getAllposts() {
        return list.getAllPosts();
    }
}
