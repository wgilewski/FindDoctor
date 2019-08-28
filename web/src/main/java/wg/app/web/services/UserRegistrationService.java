package wg.app.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wg.app.model.Role;
import wg.app.model.User;
import wg.app.persistence.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationService
{
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User registerUser(User user)
    {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }


}
