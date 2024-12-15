package com.example.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogDto {
    private String title;
    private String description;
}
