package brian.example.boot.k8s.bootexamplek8smysql.repository;

import brian.example.boot.k8s.bootexamplek8smysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContainsAndCountryContains(String name, String country);
    List<User> findByNameContains(String name);
    List<User> findByCountryContains(String country);
}
