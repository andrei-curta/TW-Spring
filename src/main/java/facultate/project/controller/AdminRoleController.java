package facultate.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminRoleController {

    @RequestMapping(value = "/adminRole", method = RequestMethod.GET)
    public String get(HttpSession session, Model model) {

        return "adminRoles";
    }

    @RequestMapping(value = "/adminRole", method = RequestMethod.POST)
    public String post(Model model) {


        return "redirect:/admin";
    }
}
