package web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String username,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password) {
        if (username.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return "redirect:/users?error=emptyFields";
        }
        userService.saveUser(new UserDto(username, lastName, email, password));
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String username,
                             @RequestParam String lastName,
                             @RequestParam String email,
                             @RequestParam String password) {
        if (username.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return "redirect:/users?error=emptyFields";
        }
        userService.saveUser(new UserDto(id, username, lastName, email, password));
        return "redirect:/users";
    }
}