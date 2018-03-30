package pl.yahoo.pawelpiedel.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class PostController {
    static Logger log = Logger.getLogger(PostController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "category", required = false) String category, Model model) {
        log.info("Jestem w controlerze");
        log.info(category);
        if (category != null && !category.isEmpty()) {
            log.info("Added category : " + category);
            model.addAttribute("category", category);
            return "index";
        } else {
            return "index";
        }


    }

}
