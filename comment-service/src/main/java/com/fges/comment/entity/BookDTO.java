package com.fges.comment.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String name;
    private String category;
}
