package com.netflixProducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflixProducer.model.Post;

@RestController
@RequestMapping("/netflix")
public class ProducerController {
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Value("${kafka.topic.name}")
    private String topicName;

    @PostMapping("/post")
    public String postMessage(@RequestBody Post post) {
    	kafkaTemplate.send(topicName, post);
        return "Netflix Post sent: " + post.getTitle();
    }
}
