//package facultate.project.controller;
//
//import facultate.project.model.Resource;
//import facultate.project.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/students/")
//public class UserController {
//    private final StudentRepository studentRepository;
//
//    @Autowired
//    public UserController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @GetMapping("signup")
//    public String showSignUpForm(Resource resource) {
//        return "add-student";
//    }
//
//    @GetMapping("list")
//    public String showUpdateForm(Model model) {
//        model.addAttribute("students", studentRepository.findAll());
//        return "user";
//    }
//
//    @PostMapping("add")
//    public String addStudent(@Valid Resource resource, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-student";
//        }
//
//        studentRepository.save(resource);
//        return "redirect:list";
//    }
//
//    @GetMapping("edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        Resource resource = studentRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//        model.addAttribute("student", resource);
//        return "update-student";
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
//    @GetMapping("delete/{id}")
//    public String deleteStudent(@PathVariable("id") long id, Model model) {
//        Resource resource = studentRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//        studentRepository.delete(resource);
//        model.addAttribute("students", studentRepository.findAll());
//        return "user";
//    }
//}
