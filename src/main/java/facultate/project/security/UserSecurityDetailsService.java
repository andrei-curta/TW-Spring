package facultate.project.security;

import facultate.project.model.User;
import facultate.project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserSecurityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByUsername(username);
        UserDetailsSecurity userDetailsSecurity = new UserDetailsSecurity(user);

        return  userDetailsSecurity;
    }
}
