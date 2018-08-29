package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper mapper;

    @Test
    public void testMapToBoards() {
        // Given
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "1", new ArrayList<>());
        TrelloBoard board = new TrelloBoard("1", "1", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>(Arrays.asList(boardDto));
        // When
        List<TrelloBoard> result = mapper.mapToBoards(trelloBoardDtoList);
        // Then
        assertEquals(1, result.size());
        assertEquals(board, result.get(0));
    }

    @Test
    public void testMapToBoardsDto() {
        // Given
        TrelloBoard board = new TrelloBoard("1", "1", new ArrayList<>());
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "1", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>(Arrays.asList(board));
        // When
        List<TrelloBoardDto> result = mapper.mapToBoardsDto(trelloBoardList);
        // Then
        assertEquals(1, result.size());
        assertEquals(boardDto, result.get(0));
    }

    @Test
    public void testMapToList() {
        // Given
        TrelloListDto listDto = new TrelloListDto("1", "1", false);
        TrelloList list = new TrelloList("1", "1", false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>(Arrays.asList(listDto));
        // When
        List<TrelloList> result = mapper.mapToList(trelloListDtoList);
        // Then
        assertEquals(1, result.size());
        assertEquals(list, result.get(0));
    }

    @Test
    public void testMapToListDto() {
        // Given
        TrelloList list = new TrelloList("1", "1", false);
        TrelloListDto listDto = new TrelloListDto("1", "1", false);
        List<TrelloList> trelloList = new ArrayList<>(Arrays.asList(list));
        // When
        List<TrelloListDto> result = mapper.mapToListDto(trelloList);
        // Then
        assertEquals(1, result.size());
        assertEquals(listDto, result.get(0));
    }

    @Test
    public void testMapToTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "desc", "top", "123");
        TrelloCard trelloCard = new TrelloCard("test", "desc", "top", "123");
        // When
        TrelloCard result = mapper.mapToTrelloCard(trelloCardDto);
        // Then
        assertEquals(trelloCard, result);
    }

    @Test
    public void testMapToTrelloCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("test", "desc", "top", "123");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "desc", "top", "123");
        // When
        TrelloCardDto result = mapper.mapToTrelloCardDto(trelloCard);
        // Then
        assertEquals(trelloCardDto, result);
    }
}