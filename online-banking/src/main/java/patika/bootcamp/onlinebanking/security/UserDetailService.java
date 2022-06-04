package patika.bootcamp.onlinebanking.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.model.User;
import patika.bootcamp.onlinebanking.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        return new UserDetail(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
