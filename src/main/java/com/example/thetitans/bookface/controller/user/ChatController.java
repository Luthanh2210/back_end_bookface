package com.example.thetitans.bookface.controller.user;

import com.example.thetitans.bookface.model.chat.Message;
import com.example.thetitans.bookface.service.user.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private IMessageService messageService;



    @PostMapping
    public ResponseEntity<Message> createNewMessage(@RequestBody Message message) {
        message.setCreatedTime(java.time.Instant.now());
        return new ResponseEntity<>(messageService.save(message), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

