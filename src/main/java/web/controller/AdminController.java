package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


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
        Set<Role> roleSet = new HashSet<>();
        for (Role roles : user.getRoles()) {
            roleSet.add(roleService.getRoleByName(String.valueOf(roles)));
        }
        userService.saveUser(new User(user.getUsername(),user.getPassword(),roleSet));
        return "redirect:users";
    }


    @GetMapping("user-edit")
    public String editUserForm(Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PostMapping("user-edit")
    public String editUser(User user){
        Set<Role> roleSetForEdit = new HashSet<>();
        for (Role roles : user.getRoles()) {
            roleSetForEdit.add(roleService.getRoleByName(String.valueOf(roles)));
        }
        user.setRoles(roleSetForEdit);
        userService.updateUser(user);
        return "redirect:users";
    }


    @GetMapping("user-delete")
    public String deleteUser(Long id) {
        userService.deleteUserById(id);
        return "redirect:users";
    }
}
