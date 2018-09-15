package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyData;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MailCreatorService {

    @Autowired
    AdminConfig adminConfig;
    @Autowired
    CompanyData company;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = getStandardContext(message);
        context.setVariable("preview", "New trello card");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyInformationEmail(String message) {
        List<String> tasks = taskRepository.findAll().stream()
                .map(Task::getContent)
                .collect(Collectors.toList());

        Context context = getStandardContext(message);
        context.setVariable("preview", "Tasks in database: " + tasks.size());
        context.setVariable("are_tasks_waiting", tasks.size() > 0);
        context.setVariable("no_tasks", "You have time to rest now!");
        context.setVariable("tasks", tasks);
        return templateEngine.process("mail/daily_task_information_mail", context);
    }

    private Context getStandardContext(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://saabviggen.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company", company.getName() + ", " + company.getGoal());
        context.setVariable("company_contact", "Contact: " + company.getEmail() +
                ", tel.: " + company.getPhone());
        context.setVariable("goodbye", "Best Regards, ");
        context.setVariable("company_name", "          " + company.getName());
        return context;
    }
}