package pl.yahoo.pawelpiedel.cms.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.yahoo.pawelpiedel.cms.dto.UserDto;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.services.user.EmailExistsException;
import pl.yahoo.pawelpiedel.cms.services.user.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }


    @RequestMapping(value = "/register/success", method = RequestMethod.GET)
    public String registerSuccess(Model model) {
        model.addAttribute("registerSucess", true);
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @RequestMapping(value = "/register/error", method = RequestMethod.GET)
    public String registerError(Model model) {
        model.addAttribute("registerError", true);
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = registerNewUser(userDto, result);
        }

        if (registered != null) {
            return "redirect:/register/success";

        } else {
            return "redirect:/register/error";
        }
    }

    private User registerNewUser(UserDto accountDto, BindingResult result) {
        User registered;
        try {
            registered = userService.registerNewUser(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

}
