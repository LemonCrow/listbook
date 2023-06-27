package com.example.listbook.repository;

import com.example.listbook.entity.Listbook;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ListbookRepository extends JpaRepository<Listbook, Long>, QuerydslPredicateExecutor<Listbook> {
}
