package com.example.demo.api;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.User;
import com.example.demo.models.requests.UserCreationRequest;
import com.example.demo.services.UserServices;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServices userService;
    
    public UserController(UserServices userService){
        this.userService = userService;

    }

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest userCreationRequests){
        return userService.createUser(userCreationRequests);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable long id){
        userService.removeUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id).orElse(null);
    }

    @GetMapping("/getall")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
