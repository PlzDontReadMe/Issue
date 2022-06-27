package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
    private String author;
    private String label;
    private String byAssignees;
    private boolean isOpened;

}


