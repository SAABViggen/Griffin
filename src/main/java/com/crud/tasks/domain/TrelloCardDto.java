package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrelloCardDto that = (TrelloCardDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(pos, that.pos) &&
                Objects.equals(listId, that.listId);
    }
}