package cc.login.tasksystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title_Task")
    private String titleTask;
    @Column(name = "description")
    private String description;
    @CreationTimestamp
    private Instant creationTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
