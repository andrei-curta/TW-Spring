package facultate.project.controller;

import facultate.project.model.User;
import facultate.project.service.UserService;
import facultate.project.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute(name = "user") User user, Model model) {

        String username = user.getUsername();
        String password = user.getPassword();

        User foundUser = userService.getUser(username, password, "user");
        User foundAdmin = userService.getUser(username, password, "admin");

        if (foundUser != null) {
            //Session.getInstance().session .setAttribute("username", username);
            //Session.getInstance().session .setAttribute("userType", "user");
           // session = Session.getInstance().session;

            String path = "redirect:/userRoles/" + foundUser.getId();
            return path;
        } else if (foundAdmin != null) {
            Session.getInstance().session .setAttribute("username", username);
            Session.getInstance().session .setAttribute("userType", "admin");
            session = Session.getInstance().session;

            return "redirect:/users";
        }

        model.addAttribute("invalidCredentials", true);
        return "login";
    }
}
