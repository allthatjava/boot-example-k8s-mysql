package brian.example.boot.k8s.bootexamplek8smysql.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String country;
    private String password;

}
