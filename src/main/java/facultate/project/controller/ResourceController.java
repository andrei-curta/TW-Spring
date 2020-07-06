package facultate.project.controller;

import facultate.project.model.Resource;
import facultate.project.model.Role;
import facultate.project.model.User;
import facultate.project.repository.ResourceRepository;
import facultate.project.repository.UserRepository;
import facultate.project.util.Session;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/resources/")
public class ResourceController {
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ResourceController(ResourceRepository resourceRepository, UserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("list")
    public String listResources(@Valid Resource resource, BindingResult result, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser =  userRepository.findFirstByUsername(currentUserName);

        List<Resource> resourceList = new ArrayList<>();
        resourceRepository.findAll().forEach(resourceList::add);
        Logger.getInstance().addToLog("ResourceaController: empty " + resourceList.isEmpty());
        model.addAttribute("resources", resourceList);

        Set<Resource> userResources= new HashSet<>();
        for (Role role : currentUser.getRoles()){
            for(Resource res : role.getResources()){
                userResources.add(res);
            }
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userREsources", userResources);
        return "resources";
    }

    @PostMapping("add")
    public String addResource(@Valid Resource resource, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-resource";
        }

        resourceRepository.save(resource);
        return "redirect:/resources/list";
    }

    @PostMapping("delete/{id}")
    public String deleteResource(@PathVariable("id") int id) {
        Logger.getInstance().addToLog("ResourceController: delete initiated for resource with ID: " + id);
        resourceRepository.deleteById(id);
        return "redirect:/resources/list";
    }

    String editText;

    @PostMapping("save/{id}")
    public String saveResource(@PathVariable("id") int id, HttpServletRequest request, String text) {
        Logger.getInstance().addToLog("ResourceController: delete initiated for resource with ID: " + id);
        Resource resource=resourceRepository.findById(id).get();
        resource.setText(request.getParameter("resourceText"));
        resourceRepository.save(resource);
        return "redirect:/resources/list";
    }
}
