package org.books.simpleonlinebookstore.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView home() {
        return new RedirectView("/api/users/");
    }
}



