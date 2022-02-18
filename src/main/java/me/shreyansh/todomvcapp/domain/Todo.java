package me.shreyansh.todomvcapp.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {
    private int id;
    private String description;
    private boolean completed;
}
