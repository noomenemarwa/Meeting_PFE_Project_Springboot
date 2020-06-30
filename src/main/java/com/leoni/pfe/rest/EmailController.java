package com.leoni.pfe.rest;

import com.leoni.pfe.Email.Feedback;
import com.leoni.pfe.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
/*@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})*/
public class EmailController {


    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/sendEmail")
    public HttpStatus sendFeedback(@Valid @RequestBody Feedback feedback,
                                   BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }
        boolean res = emailService.sendEmail(feedback);
        if (res) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
