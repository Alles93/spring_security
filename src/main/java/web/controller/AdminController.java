package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;


    private User user;

    @GetMapping(value = "users")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @GetMapping(value = "user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping(value = "user-create")
    public String createUser(User user)   {
        userService.saveUser(user);
        return "redirect:users";
    }


    @GetMapping("user-edit")
    public String editUserForm(Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PostMapping("user-edit")
    public String editUser(User user){
        userService.updateUser(user);
        return "redirect:users";
    }


    @GetMapping("user-delete")
    public String deleteUser(Long id) {
        userService.deleteUserById(id);
        return "redirect:users";
    }
}
