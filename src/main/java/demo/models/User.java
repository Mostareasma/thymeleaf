package demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username ;
    @Column(unique = true)
    private String email ;
    private String password ;
    private String gender ;
    private String phone;

    // @ManyToOne(cascade = CascadeType.MERGE)
    // @JoinColumn(name = "role_id", referencedColumnName = "id")
    // private Role role;


    // @CreationTimestamp
    // private Date createdAt;

    // @UpdateTimestamp
    // private Date updatedAt;
}