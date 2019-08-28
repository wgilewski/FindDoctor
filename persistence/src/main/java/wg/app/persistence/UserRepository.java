package wg.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wg.app.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUsername(String username);
}
