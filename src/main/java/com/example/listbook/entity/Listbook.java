package com.example.listbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Listbook extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String author;

    @Column(length = 1500, nullable = false)
    private String detail;

    @Column(nullable = false)
    private int price;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeAuthor(String author){
        this.author = author;
    }

    public void changePrice(int price){
        this.price = price;
    }

    public void changeDetail(String detail){
        this.detail = detail;
    }

}
