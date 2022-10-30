package appsprototyping.controllers;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Show {
    public static final Logger log = LoggerFactory.getLogger(Show.class);

    @GetMapping("/login-form")
    public String loginForm() {
        log.trace("Hit log form");
        return "login-form";
    }

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("index");

        ViewModel viewModel = new ViewModel();
        viewModel.setName("Darling!");
        viewModel.setLinkToHelp("help.txt");
        viewModel.setLinkToRest("welcome");
        viewModel.setLinkToRest("welcome");
        viewModel.setLinkToFormProtected("form-protected/index");

        modelAndView.addObject("viewmodel", viewModel);
        return modelAndView;
    }

    @Data
    public static class ViewModel {
        private String name;
        private String linkToHelp;
        private String linkToRest;
        private String linkToFormProtected;
    }
}

