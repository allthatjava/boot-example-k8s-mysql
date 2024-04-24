package brian.example.boot.k8s.bootexamplek8smysql.controller;

import brian.example.boot.k8s.bootexamplek8smysql.dto.SearchWord;
import brian.example.boot.k8s.bootexamplek8smysql.dto.UserDto;
import brian.example.boot.k8s.bootexamplek8smysql.entity.User;
import brian.example.boot.k8s.bootexamplek8smysql.repository.UserRepository;
import brian.example.boot.k8s.bootexamplek8smysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@RequestBody User user){
        UserDto userDto = service.saveUser(user);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(service.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        UserDto userDto = service.findById(id);
        if( userDto == null )
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Deleted Item with id:"+id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestBody SearchWord searchWord){
        List<UserDto> foundList = service.findBySearchWord(searchWord);

        if( foundList.isEmpty() )
            return ResponseEntity.ok(foundList);
        else
            return ResponseEntity.notFound().build();
    }
}
