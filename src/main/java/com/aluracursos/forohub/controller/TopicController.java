package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.model.Topic;
import com.aluracursos.forohub.repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public List<Topic> getAllTopics() {

        return topicRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {

        Optional<Topic> topic = topicRepository.findById(id);

        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@Valid @RequestBody Topic topic) {

        Topic savedTopic = topicRepository.save(topic);

        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @Valid @RequestBody Topic topicDetails) {
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {

            Topic existingTopic = topic.get();
            existingTopic.setTitle(topicDetails.getTitle());
            existingTopic.setContent(topicDetails.getContent());
            Topic updatedTopic = topicRepository.save(existingTopic);

            return ResponseEntity.ok(updatedTopic);

        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {

        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {

            topicRepository.delete(topic.get());
            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
