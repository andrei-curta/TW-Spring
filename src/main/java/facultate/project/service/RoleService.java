package facultate.project.service;

import facultate.project.model.Role;
import facultate.project.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/role")
@RestController
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping(value="/all")
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
