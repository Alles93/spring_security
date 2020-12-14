package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

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


    @GetMapping(value = "newUser")
    public String getUser() {
        return "addUser";
    }

    @PostMapping(value = "new")
    public String addNewUser(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        userService.updateUser(new User(username, password, roleSet ));
        return "redirect:users";
    }


    @GetMapping("edit")
    public String editPage(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("editSave")
    public String editUser(Model model,
                           @RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String[] role){
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        userService.updateUser(new User(id,username,password,roleSet));
        return "redirect:users";
    }


    @GetMapping("delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Long userId = Long.parseLong(id);
        userService.deleteUser(userId);
        return "redirect:users";
    }
}
