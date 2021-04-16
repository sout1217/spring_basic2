package vuejs.springboot.mysql.backend.domain.model.account;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode(of = {"username", "emailAddress"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name="email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    // 암호화 시 길이가 60글자 되기 때문에 변경하였음
    @Column(name="password", nullable = false, length = 60)
    private String password;

    @Column(name="first_name", nullable = false, length = 45)
    @Builder.Default
    private String firstName = "";

    @Column(name="last_name", nullable = false, length = 45)
    @Builder.Default
    private String lastName = "";

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
