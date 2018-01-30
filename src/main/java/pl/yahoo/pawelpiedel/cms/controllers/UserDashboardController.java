package pl.yahoo.pawelpiedel.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {
    @GetMapping("/user")
    public String user(){
        return "user";
    }
}
