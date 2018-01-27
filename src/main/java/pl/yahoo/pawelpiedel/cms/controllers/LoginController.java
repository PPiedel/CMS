package pl.yahoo.pawelpiedel.cms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.yahoo.pawelpiedel.cms.dto.UserDto;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @RequestMapping(value = "/login/success", method = RequestMethod.GET)
    public String loginSuccess(Model model) {
        logger.debug("Login success");

        model.addAttribute("user", new UserDto());
        model.addAttribute("success", true);

        return "login";
    }

    @RequestMapping(value = "/login/error", method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("error", true);
        return "login";
    }

    @RequestMapping(value = "/logout/successful", method = RequestMethod.GET)
    public String logoutSuccessful(Model model) {
        logger.debug("Logout success");

        model.addAttribute("user", new UserDto());
        model.addAttribute("logout", true);
        return "login";
    }


    @RequestMapping(value = "/access/denied", method = RequestMethod.GET)
    public String accessDenied() throws Exception {
        throw new Exception("You don't have permissions to see this page.");
    }

}
