package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrelloList list = (TrelloList) o;
        return isClosed == list.isClosed &&
                Objects.equals(id, list.id) &&
                Objects.equals(name, list.name);
    }
}