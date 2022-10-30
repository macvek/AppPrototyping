package appsprototyping.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormProtected {
    public static final Logger log = LoggerFactory.getLogger(FormProtected.class);

    @GetMapping("/form-protected-login")
    public String loginForm() {
        SecurityContext context = SecurityContextHolder.getContext();
        log.info("Has authentication?: "+(context.getAuthentication() != null));
        return "form-protected-login";
    }

    @GetMapping("/form-protected/index")
    public String index() {
        SecurityContext context = SecurityContextHolder.getContext();
        log.info(context.getAuthentication().getName());
        log.info(""+context.getAuthentication().getPrincipal());
        log.info(""+context.getAuthentication().getCredentials());
        log.info(""+context.getAuthentication().getAuthorities());

        return "form-protected-index";
    }

}
