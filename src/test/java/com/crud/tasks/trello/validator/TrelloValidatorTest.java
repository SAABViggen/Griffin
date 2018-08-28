package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @InjectMocks
    @Spy
    private TrelloValidator validator;

    @Test
    public void shouldValidateCard() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Card", "Some card", "top", "123");
        // When
        validator.validateCard(trelloCard);
        // Then
        verify(validator).validateCard(trelloCard);
    }

    @Test
    public void shouldFetchFilteredBoards() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Some List", false));

        TrelloBoard shouldPass = new TrelloBoard("1", "Some Task", trelloLists);
        TrelloBoard shouldBeRejected = new TrelloBoard("2", "Test", trelloLists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(shouldPass);
        trelloBoards.add(shouldBeRejected);
        // When
        List<TrelloBoard> result = validator.validateTrelloBoards(trelloBoards);
        // Then
        assertEquals(1, result.size());
        assertEquals(shouldPass, result.get(0));
    }
}