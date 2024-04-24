package brian.example.boot.k8s.bootexamplek8smysql.service;

import brian.example.boot.k8s.bootexamplek8smysql.dto.SearchWord;
import brian.example.boot.k8s.bootexamplek8smysql.dto.UserDto;
import brian.example.boot.k8s.bootexamplek8smysql.entity.User;
import brian.example.boot.k8s.bootexamplek8smysql.mapper.UserMapper;
import brian.example.boot.k8s.bootexamplek8smysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository repo;
    private UserMapper mapper;

    @Autowired
    public UserService(UserRepository repo, UserMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }

    public UserDto saveUser(User user) {
        User userNew =repo.save(user);
        return mapper.apply(userNew);
    }

    public List<UserDto> getAllUser(){
        List<User> allUsers = repo.findAll();
        return allUsers.stream().map(mapper).collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        Optional<User> user = repo.findById(id);
        return user.map(value -> mapper.apply(value)).orElse(null);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<UserDto> findBySearchWord(SearchWord searchWord) {
        if( !searchWord.getName().isEmpty() && !searchWord.getCountry().isEmpty()) {
            return repo.findByNameContainsAndCountryContains(searchWord.getName(), searchWord.getCountry())
                    .stream().map(mapper).collect(Collectors.toList());
        }else if (!searchWord.getName().isEmpty()) {
            return repo.findByNameContains(searchWord.getName())
                    .stream().map(mapper).collect(Collectors.toList());
        }else if (!searchWord.getCountry().isEmpty()) {
            return repo.findByCountryContains(searchWord.getCountry())
                    .stream().map(mapper).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }
}
