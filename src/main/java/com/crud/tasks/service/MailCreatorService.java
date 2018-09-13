package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Value("${info.company.name}")
    private String companyName;
    @Value("${info.company.goal}")
    private String companyGoal;
    @Value("${info.company.email}")
    private String companyEmail;
    @Value("${info.company.phone}")
    private String companyPhone;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://saabviggen.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "New trello card");
        context.setVariable("company", companyName + ", " + companyGoal + ".\n" +
                "Contact: " + companyEmail + ", tel.: " + companyPhone);
        context.setVariable("goodbye", "Best Regards, \n" + companyName);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}