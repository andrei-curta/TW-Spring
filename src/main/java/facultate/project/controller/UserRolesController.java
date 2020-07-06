package facultate.project.controller;

import facultate.project.model.Resource;
import facultate.project.model.Right;
import facultate.project.model.Role;
import facultate.project.model.User;
import facultate.project.repository.ResourceRepository;
import facultate.project.repository.RightRepository;
import facultate.project.repository.RoleRepository;
import facultate.project.repository.UserRepository;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/userRoles/")
public class UserRolesController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    RightRepository rightRepository;

    @GetMapping("{idUser}/{idRole}/{idResource}/{idRight}")
    public String addRole(@PathVariable("idUser") int idUser,
                          @PathVariable("idRole") int idRole,
                          @PathVariable("idResource") int idResource,
                          @PathVariable("idRight") int idRight) {

        Right right = rightRepository.findById(idRight).get();
        Resource resource = resourceRepository.findById(idResource).get();
        Role role = roleRepository.findById(idRole).get();
        User user = userRepository.findById(idUser).get();

        role.addResource(resource);
        role.addRight(right);
        roleRepository.save(role);
        user.addRole(role);
        userRepository.save(user);

        return "redirect:userRoles";
    }

    @GetMapping("{idUser}")
    public String rolesList(@PathVariable("idUser") int idUser, Model model) {
        Role newRole = new Role ();
        model.addAttribute("newRole", newRole);
        model.addAttribute("idUser", idUser);
        //resources
        List<Resource> resourceList = new ArrayList<>();
        resourceRepository.findAll().forEach(resourceList::add);
        model.addAttribute("resources", resourceList);

        //rights
        List<Right> rightList = new ArrayList<>();
        rightRepository.findAll().forEach(rightList::add);
        model.addAttribute("rights", rightList);

        //roles
        List<Role> rolesList = new ArrayList<>();
        roleRepository.findAll().forEach(rolesList::add);
        model.addAttribute("roles", rolesList);

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + idUser));
        model.addAttribute("user", user);

     //   Set<Role> roles = user.getRoles();

        //userRepository.addUser

        return "userRoles";
    }

    @PostMapping("{idUser}/role/{idRole}/delete")
    public String deleteRole(@PathVariable("idUser") Integer idUser,
                             @PathVariable("idRole") Integer idRole){

        roleRepository.deleteById(idRole);
        String path = "redirect:/userRoles/" + idUser.toString();
        return path;
    }

    @PostMapping("{idUser}/role/add")
    public String addRole(@PathVariable("idUser") Integer idUser,
                          HttpServletRequest request){
        String name =  request.getParameter("name");
        roleRepository.save(new Role(name));
        String path = "redirect:/userRoles/" + idUser.toString();
        return path;
    }
}
