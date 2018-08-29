package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;
}