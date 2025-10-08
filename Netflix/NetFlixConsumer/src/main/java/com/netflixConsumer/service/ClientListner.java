package com.netflixConsumer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.netflixConsumer.model.Post;

@Service
public class ClientListner {
	private final List<Post> receivedPosts = new ArrayList<>();
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "netflix-consumer-group", containerFactory = "kafkaListenerContainerFactory")
	public void consume(Post post) {
		System.out.println("New Netflix Post Received: " + post);
		receivedPosts.add(post);
    }
	
	public List<Post> getAllPosts() {
		return receivedPosts;
    }
}
