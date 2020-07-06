//package facultate.project.controller;
//
//
//import facultate.project.model.Resource;
//import facultate.project.model.Right;
//import facultate.project.model.Role;
//import facultate.project.model.User;
//import facultate.project.repository.RightRepository;
//import facultate.project.repository.RoleRepository;
//import facultate.project.repository.StudentRepository;
//import facultate.project.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin/")
//public class AdminController {
//    private final UserRepository userRepository;
//    private final StudentRepository studentRepository;
//    private final RoleRepository roleRepository;
//    private final RightRepository rightRepository;
//
//
//    @Autowired
//    public AdminController(UserRepository userRepository, StudentRepository studentRepository, RoleRepository roleRepository, RightRepository rightRepository) {
//        this.userRepository = userRepository;
//        this.studentRepository = studentRepository;
//        this.roleRepository = roleRepository;
//        this.rightRepository = rightRepository;
//    }
//
//    @GetMapping("list")
//    public String showUpdateForm(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        model.addAttribute("roles", roleRepository.findAll());
//
//        return "admin";
//    }
//
//    @GetMapping("role")
//    public String showRoleForm(Model model) {
//        model.addAttribute("resources", studentRepository.findAll());
//        model.addAttribute("student", new Resource());
//
//        return "add-role";
//    }
//
//    @RequestMapping(value = "addRole", method = RequestMethod.POST)
//    public String addRole(@ModelAttribute("student") Resource resource,
//                          @RequestParam(value = "isCreated", required = false) Boolean isCreated,
//                          @RequestParam(value = "isReaded", required = false) Boolean isReaded,
//                          @RequestParam(value = "isUpdated", required = false) Boolean isUpdated,
//                          @RequestParam(value = "isDeleted", required = false) Boolean isDeleted,
//                          BindingResult result,
//                          Model model) {
//        if (result.hasErrors()) {
//            return "add-role";
//        }
//
//        List<Right> rights = new ArrayList<>();
//        if(isCreated != null){
//            rights.add(rightRepository.findByName("CREATE").get(0));
//        }
//        if(isReaded != null){
//            rights.add(rightRepository.findByName("READ").get(0));
//        }
//        if(isUpdated != null){
//            rights.add(rightRepository.findByName("UPDATE").get(0));
//        }
//        if(isDeleted != null){
//            rights.add(rightRepository.findByName("DELETE").get(0));
//        }
//
//        Role role = new Role();
//        role.setResource(studentRepository.findById(resource.getId()).get());
//        role.setRightList(rights);
//
//        roleRepository.save(role);
//
//        return "redirect:list";
//    }
//
//    @GetMapping("editUser/{id}")
//    public String showUserForm(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("user", user);
//        model.addAttribute("resources", studentRepository.findAll());
//
//        return "adminResources";
//    }
//
//    @GetMapping("editResource/{id}")
//    public String showResourceForm(@PathVariable("id") long id, Model model) {
//        Resource resource = studentRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//
//        List<String> rights = new ArrayList<>();
//        rights.add("CREATE");
//        rights.add("READ");
//        rights.add("UPDATE");
//        rights.add("DELETE");
//
//        model.addAttribute("student", resource);
//        model.addAttribute("rightsName", rights);
//
//        return "adminRoles";
//    }
//
//    @GetMapping("editRights/{id}")
//    public String showRightsForm(@PathVariable("id") long id, Model model) {
//        Resource resource = studentRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//
//        List<String> rights = new ArrayList<>();
//        rights.add("CREATE");
//        rights.add("READ");
//        rights.add("UPDATE");
//        rights.add("DELETE");
//
//        model.addAttribute("student", resource);
//        model.addAttribute("rightsName", rights);
//
//        return "adminRoles";
//    }
//
//    @PostMapping("update/{id}")
//    public String updateStudent(@PathVariable("id") long id, @Valid Resource resource, BindingResult result,
//                                Model model) {
//        if (result.hasErrors()) {
//            resource.setId((int) id);
//            return "update-student";
//        }
//
//        studentRepository.save(resource);
//        model.addAttribute("students", studentRepository.findAll());
//        return "user";
//    }
//
//    @GetMapping("deleteUser/{id}")
//    public String deleteUser(@PathVariable("id") long id, Model model) {
//        Resource resource = studentRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//        studentRepository.delete(resource);
//        model.addAttribute("students", studentRepository.findAll());
//        return "user";
//    }
//
//    @GetMapping("delete/{id}")
//    public String deleteStudent(@PathVariable("id") long id, Model model) {
//        Resource resource = studentRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//        studentRepository.delete(resource);
//        model.addAttribute("students", studentRepository.findAll());
//        return "user";
//    }
//}
//
////@Controller
////public class AdminController {
////
////    @Autowired
////    private UserService userService;
////
////    @Autowired
////    private UserRepository userRepository;
////    @Autowired
////    private RoleRepository roleRepository;
////
////
////    public String username = "";
////    public User selectedUser;
////
////    @RequestMapping(value = "/admin", method = RequestMethod.GET)
////    public String dropdownUsers(HttpSession session, Model model) {
////        ArrayList<String> users = new ArrayList<String>();
////
////        for (User user : userService.getUsers()) {
////            users.add(user.getUsername());
////        }
////        model.addAttribute("usernames", users);
////        model.addAttribute("username", username);
////        model.addAttribute("users", userService.getUsers());
////        model.addAttribute("selectedUser", selectedUser);
////
//////        Role rol1 = roleRepository.findAll().get(0);
//////        Role rol2 = roleRepository.findAll().get(1);
//////        Role rol3 = roleRepository.findAll().get(2);
//////        Role rol4 = roleRepository.findAll().get(3);
////
//////        List<Role> roles1 = new ArrayList<>();
//////        roles1.add(rol1);
//////        roles1.add(rol3);
//////        roles1.add(rol4);
//////
//////        User user1 = userRepository.getOne(1);
//////        user1.setRoleList(roles1);
//////
//////        List<Role> roles2 = new ArrayList<>();
//////        roles2.add(rol2);
//////
//////        User user2 = userRepository.getOne(2);
//////        user2.setRoleList(roles2);
//////
//////        //userRepository.save(user1);
//////        userRepository.save(user2);
////
////
////
////
//////        Right create = new Right();
//////        create.setRightType("CREATE");
//////        Right read = new Right();
//////        read.setRightType("READ");
//////        Right update = new Right();
//////        update.setRightType("UPDATE");
//////        Right delete = new Right();
//////        delete.setRightType("DELETE");
//////
//////        List<Right> rights1 = new ArrayList<>();
//////        rights1.add(read);
//////        rights1.add(update);
//////
//////        Student student = studentRepository.findById(6).get();
//////
//////        Role role1 = new Role();
//////        role1.setStudent(student);
//////        role1.setRightList(rights1);
//////
//////        List<Role> roles = new ArrayList<>();
//////        roles.add(role1);
//////
//////        User user = new User();
//////        user.setUsername("user");
//////        user.setPassword("user");
//////        user.setType("user");
//////        user.setRoleList(roles);
//////
//////        userService.saveUser(user);
////
////        return "admin";
////    }
////
////    @RequestMapping(value = "/admin", method = RequestMethod.POST)
////    public String post(HttpSession session, Model model) {
////
////        System.out.println(username);
////
////        return "admin";
////    }
////
////    //private StudentRepository studentRepository;
////
//////    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//////    public String Hello(Model model) {
//////        Right create = new Right();
//////        create.setRightType("CREATE");
//////        Right read = new Right();
//////        read.setRightType("READ");
//////        Right update = new Right();
//////        update.setRightType("UPDATE");
//////        Right delete = new Right();
//////        delete.setRightType("DELETE");
//////
//////        List<Right> rights1 = new ArrayList<>();
//////        rights1.add(read);
//////        rights1.add(update);
//////
//////        Role role1 = new Role();
//////        role1.setStudent(studentRepository.findById(4).get());
//////        role1.setRightList(rights1);
//////
//////        List<Role> roles = new ArrayList<>();
//////        roles.add(role1);
//////
//////        User user = new User();
//////        user.setUsername("user");
//////        user.setPassword("user");
//////        user.setType("user");
//////        user.setRoleList(roles);
//////
//////        userService.saveUser(user);
//////        model.addAttribute("users", userService.getUsers());
//////
//////        return "admin";
//////    }
////}
