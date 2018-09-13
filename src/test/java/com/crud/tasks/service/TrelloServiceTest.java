package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchTrelloBoards() {
        // Given
        TrelloBoardDto trelloBoard = new TrelloBoardDto();
        List<TrelloBoardDto> list = new ArrayList<>();
        list.add(trelloBoard);
        when(trelloClient.getTrelloBoards()).thenReturn(list);
        // When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        // Then
        assertEquals(1, result.size());
        assertEquals(list, result);
    }

    @Test
    public void shouldCreateNewCard() {
        // Given
        TrelloCardDto trelloCard = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com");

        Mail mail = new Mail(
                "test@test.com",
                null,
                "Tasks: New Trello card",
                "New card: Test task has been created on Your Trello account");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        when(trelloClient.createNewCard(trelloCard)).thenReturn(createdTrelloCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");
        // When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCard);
        // Then
        assertEquals("1", result.getId());
        assertEquals("Test task", result.getName());
        assertEquals("http://test.com", result.getShortUrl());
        verify(emailService, times(1)).send(mail, false);
    }
}