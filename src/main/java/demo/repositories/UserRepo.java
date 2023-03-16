package demo.repositories;

import demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);

    User findUserById(long id);
}
