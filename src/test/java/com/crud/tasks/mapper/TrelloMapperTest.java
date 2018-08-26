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
        TrelloBoardDto boardDto1 = new TrelloBoardDto("1", "1", new ArrayList<>());
        TrelloBoardDto boardDto2 = new TrelloBoardDto("2", "2", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>(Arrays.asList(boardDto1, boardDto2));
        // When
        List<TrelloBoard> result = mapper.mapToBoards(trelloBoardDtoList);
        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("2", result.get(1).getId());
        assertEquals("1", result.get(0).getName());
        assertEquals("2", result.get(1).getName());
        result.forEach(trelloBoard -> assertEquals(0, trelloBoard.getLists().size()));
    }

    @Test
    public void testMapToBoardsDto() {
        // Given
        TrelloBoard board1 = new TrelloBoard("1", "1", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2", "2", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>(Arrays.asList(board1, board2));
        // When
        List<TrelloBoardDto> result = mapper.mapToBoardsDto(trelloBoardList);
        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("2", result.get(1).getId());
        assertEquals("1", result.get(0).getName());
        assertEquals("2", result.get(1).getName());
        result.forEach(trelloBoardDto -> assertEquals(0, trelloBoardDto.getLists().size()));
    }

    @Test
    public void testMapToList() {
        // Given
        TrelloListDto listDto1 = new TrelloListDto("1", "1", true);
        TrelloListDto listDto2 = new TrelloListDto("2", "2", false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>(Arrays.asList(listDto1, listDto2));
        // When
        List<TrelloList> result = mapper.mapToList(trelloListDtoList);
        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("2", result.get(1).getId());
        assertEquals("1", result.get(0).getName());
        assertEquals("2", result.get(1).getName());
        assertTrue(result.get(0).isClosed());
        assertFalse(result.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // Given
        TrelloList list1 = new TrelloList("1", "1", true);
        TrelloList list2 = new TrelloList("2", "2", false);
        List<TrelloList> trelloList = new ArrayList<>(Arrays.asList(list1, list2));
        // When
        List<TrelloListDto> result = mapper.mapToListDto(trelloList);
        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("2", result.get(1).getId());
        assertEquals("1", result.get(0).getName());
        assertEquals("2", result.get(1).getName());
        assertTrue(result.get(0).isClosed());
        assertFalse(result.get(1).isClosed());
    }

    @Test
    public void testMapToTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "desc", "top", "123");
        // When
        TrelloCard result = mapper.mapToTrelloCard(trelloCardDto);
        // Then
        assertEquals("test", result.getName());
        assertEquals("desc", result.getDescription());
        assertEquals("top", result.getPos());
        assertEquals("123", result.getListId());
    }

    @Test
    public void testMapToTrelloCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("test", "desc", "top", "123");
        // When
        TrelloCardDto result = mapper.mapToTrelloCardDto(trelloCard);
        // Then
        assertEquals("test", result.getName());
        assertEquals("desc", result.getDescription());
        assertEquals("top", result.getPos());
        assertEquals("123", result.getListId());
    }
}