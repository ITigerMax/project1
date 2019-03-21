package tk.itiger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tk.itiger.model.User;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private List<User> users = new ArrayList<>();

    @GetMapping("/view")
    public String view(@RequestParam(value = "name", required = false, defaultValue = "stranger") String name, Model model) {
        model.addAttribute("msg", "Hello " + name + "!!!");
        return "/index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw() {
        return "raw data";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", users);
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp() {
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute User user) {
        users.add(user);
        System.out.println("Add user number: " + users.size());
        return "redirect:/users";
    }

}
