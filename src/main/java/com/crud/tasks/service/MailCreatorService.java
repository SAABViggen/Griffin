package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    AdminConfig adminConfig;
    @Autowired
    TaskRepository taskRepository;
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
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://saabviggen.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("preview", "New trello card");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company", companyName + ", " + companyGoal);
        context.setVariable("company_contact", "Contact: " + companyEmail + ", tel.: " + companyPhone);
        context.setVariable("goodbye", "Best Regards, ");
        context.setVariable("company_name", "          " + companyName);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyInformationEmail(String message) {
        List<String> tasks = new ArrayList<>();
        taskRepository.findAll().stream()
                .map(Task::getContent)
                .forEach(tasks::add);

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://saabviggen.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("preview", "Tasks in database: " + taskRepository.count());
        context.setVariable("are_tasks_waiting", taskRepository.count() > 0);
        context.setVariable("no_tasks", "You have time to rest now!");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company", companyName + ", " + companyGoal);
        context.setVariable("company_contact", "Contact: " + companyEmail + ", tel.: " + companyPhone);
        context.setVariable("goodbye", "Best Regards, ");
        context.setVariable("company_name", "          " + companyName);
        context.setVariable("tasks", tasks);
        return templateEngine.process("mail/daily_task_information_mail", context);
    }
}