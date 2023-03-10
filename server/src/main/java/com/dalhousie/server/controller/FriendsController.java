package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IFriendRepository;
import com.dalhousie.server.model.Friends;
import com.dalhousie.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {
    
    @Autowired
    private IFriendRepository friendRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createFriend(@RequestBody Friends friends) {
        if(friendRepository.save(friends) > 0) {
            return new ResponseEntity<>("Friend created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Friends> getAllFriends(){
        return friendRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friends> get(@PathVariable Integer id) {
        return friendRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFriend(@PathVariable Integer id, @RequestBody Friends friends) {
        return friendRepository.getById(id)
        .map(savedFriend -> {
            savedFriend.setId(friends.getId());
            savedFriend.setUserId(friends.getUserId());
            savedFriend.setTargetUserId(friends.getTargetUserId());

            friendRepository.update(savedFriend);
            return new ResponseEntity<>("Friend updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(friendRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Friend deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all/{id}")
    public List<User> getAllFriends(@PathVariable Integer id){
        return friendRepository.getAllFriendsByUserId(id);
    }
}
