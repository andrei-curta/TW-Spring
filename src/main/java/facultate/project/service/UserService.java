package facultate.project.service;

import facultate.project.model.User;
import facultate.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username, String password, String type){
        for (User user: userRepository.findAll()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password) && user.getType().equals(type))
                return user;
        }

        return null;
    }

    public boolean isUsernameTaken(String username){
        for (User user: userRepository.findAll()) {
            if(user.getUsername().equals(username))
                return true;
        }

        return false;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        for(User user: userRepository.findAll()){
            if(user.getType().equals("user"))
                users.add(user);
        }

        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void addUser(String username, String password, String type){
        User user = new User(username, passwordEncoder.encode(password), type);
        userRepository.save(user);
    }
}
