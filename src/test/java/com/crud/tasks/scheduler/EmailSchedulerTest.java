/*
package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @SpyBean
    private SimpleEmailService simpleEmailService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void sendInformationEmailTest() {
        // Given
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");
        when(taskRepository.count()).thenReturn(5L);
        // When
        emailScheduler.sendInformationEmail();
        // Then
        verify(simpleEmailService, times(1)).
                send(argThat(new MailMatcher(
                        new Mail(
                                "mail@mail.com",
                                null,
                                "Tasks: Once a day email",
                                "Currently in database you got: 4 tasks"))), eq(MailType.DAILY_MAIL));
    }

    private class MailMatcher implements ArgumentMatcher<Mail> {
        private final Mail expected;

        public MailMatcher(Mail expected) {
            this.expected = expected;
        }

        @Override
        public boolean matches(Mail mail) {
            return mail.getMailTo().equals(expected.getMailTo())
                    && mail.getSubject().equals(expected.getSubject())
                    && mail.getMessage().equals(expected.getMessage());
        }
    }
}*/
