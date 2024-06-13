package com.spring.redis.Repo;

import com.spring.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepo {
    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    private static final String KEY = "USER";

    public ResponseEntity<?> saveUser(User user) {
        this.redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return new ResponseEntity<>("Data Saved Success", HttpStatus.OK);
    }
    public User getUser(String userId) {
       return (User) this.redisTemplate.opsForHash().get(KEY, userId);
    }
    public ResponseEntity<?> findAll(){
        Map<Object,Object>alldata=this.redisTemplate.opsForHash().entries(KEY);
        Collection<Object> values=alldata.values();
        List<User>collect=values.stream().map(value->(User)value).collect(Collectors.toList());
       return new ResponseEntity<>(collect,HttpStatus.OK);
    }
    public  ResponseEntity<?>  deleteUser(String userId){
        this.redisTemplate.opsForHash().delete(KEY, userId);
        return new ResponseEntity<>("Data Deleted Success", HttpStatus.OK);
    }
    public ResponseEntity<?> updateUser(User user) {
        User existingUser = getUser(user.getUserId());
        if (existingUser != null) {
            // Update the user's data
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            saveUser(existingUser);
            return new ResponseEntity<>("Data Updated Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
