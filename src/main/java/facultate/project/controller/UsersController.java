package facultate.project.controller;

import facultate.project.model.User;
import facultate.project.repository.ResourceRepository;
import facultate.project.repository.UserRepository;
import facultate.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    private final UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public String UsersLogin(Model model) {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        model.addAttribute("users", userList);
        return "users";
    }

}
