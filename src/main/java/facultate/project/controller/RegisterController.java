package facultate.project.controller;

import facultate.project.model.User;
import facultate.project.service.UserService;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        model.addAttribute("blankFields", false);
        model.addAttribute("passwordsDontMatch", false);
        model.addAttribute("usernameTaken", false);

        if (username.isBlank() || password1.isBlank() || password2.isBlank()) {
            model.addAttribute("blankFields", true);
            Logger.getInstance().addToLog("Register: blank fields");
            return "register";
        }

        if (!password1.equals(password2)) {
            model.addAttribute("passwordsDontMatch", true);
            Logger.getInstance().addToLog("Register: passwords don't match");
            return "register";
        }

        if (userService.isUsernameTaken(username)) {
            model.addAttribute("usernameTaken", true);
            Logger.getInstance().addToLog("Register: username " + username + " is taken");
            return "register";
        } else {
            userService.addUser(username,  password1, "user");
            Logger.getInstance().addToLog("Register: user " + username + " added");
            return "redirect:login";
        }

    }
}
