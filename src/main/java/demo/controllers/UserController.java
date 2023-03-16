package demo.controllers;


import demo.models.User;
import demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/add-user")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        try {
            userService.saveUser(user);
            log.info("New User has been added with name: " + user.getUsername() + " and email: " + user.getEmail());
        } catch (Exception e) {
            log.error("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        try {
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "edit-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute User user) {
        try{
            log.info(String.valueOf(id)+ user);
            userService.editUser(id, user);
            log.info("Use modified successfully");
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}