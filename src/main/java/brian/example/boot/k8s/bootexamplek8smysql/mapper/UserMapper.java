package brian.example.boot.k8s.bootexamplek8smysql.mapper;

import brian.example.boot.k8s.bootexamplek8smysql.dto.UserDto;
import brian.example.boot.k8s.bootexamplek8smysql.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user){
        return new UserDto(user.getId(), user.getName(), user.getCountry());
    }
}
