package appsprototyping.controllers;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Show {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        ViewModel viewModel = new ViewModel();
        viewModel.setName("Darling!");
        viewModel.setLinkToHelp("help.txt");
        viewModel.setLinkToRest("welcome");

        modelAndView.addObject("viewmodel", viewModel);
        return modelAndView;
    }

    @Data
    public static class ViewModel {
        private String name;
        private String linkToHelp;
        private String linkToRest;
    }
}

