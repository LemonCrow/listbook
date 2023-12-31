package com.example.listbook.dto;

import com.example.listbook.entity.Listbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListbookDTO {
    private Long idx;
    private String title;
    private String author;
    private String detail;
    private int price;
    private LocalDateTime regDate, modDate;
}
