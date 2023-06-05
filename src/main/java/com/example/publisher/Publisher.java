package com.example.publisher;

import com.example.dto.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {

    private RedisTemplate template;
    private ChannelTopic topic;

    public Publisher(RedisTemplate template, ChannelTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    @PostMapping("/publish")
    public String publish(@RequestBody Product product) {
        template.convertAndSend(topic.getTopic(), product.toString());
        return "Event published";
    }
}
