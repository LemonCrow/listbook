package com.example.listbook.repository;

import com.example.listbook.entity.Listbook;
import com.example.listbook.entity.QListbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ListbookRepositoryTests {
    @Autowired
    private  ListbookRepository listbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Listbook guestbook = Listbook.builder()
                    .title("Title..." + i)
                    .detail("책 내용..." + i)
                    .author("저작자" + (i % 10))
                    .price(8000)
                    .build();
            System.out.println(listbookRepository.save(guestbook));
        });
    }



    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("idx").descending());
        QListbook qListbook = QListbook.listbook;
        String keyword = "7";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qListbook.title.contains(keyword);
        builder.and(expression);
        Page<Listbook> result = listbookRepository.findAll(builder, pageable);
        result.stream().forEach(listbook -> {
            System.out.println(listbook);
        });
    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("idx").ascending());
        QListbook qListbook = QListbook.listbook;
        String keyword = "7";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expTitle = qListbook.title.contains(keyword);
        BooleanExpression expWriter = qListbook.author.contains(keyword);
        BooleanExpression expAll = expTitle.and(expWriter);
        builder.and(expAll);
        builder.and(qListbook.idx.gt(50L));
        Page<Listbook> result = listbookRepository.findAll(builder, pageable);
        result.stream().forEach(listbook -> {
            System.out.println(listbook);
        });
    }
}
