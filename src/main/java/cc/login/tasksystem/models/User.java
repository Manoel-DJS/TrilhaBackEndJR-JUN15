package cc.login.tasksystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userid;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @CreationTimestamp
    private Instant creationTimestamp;
}
