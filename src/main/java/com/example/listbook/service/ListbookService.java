package com.example.listbook.service;

import com.example.listbook.dto.ListbookDTO;
import com.example.listbook.dto.PageRequestDTO;
import com.example.listbook.dto.PageResultDTO;
import com.example.listbook.entity.Listbook;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;

public interface ListbookService {

    Long register(ListbookDTO dto);
    PageResultDTO<ListbookDTO, Listbook> getList(PageRequestDTO requestDTO);
    ListbookDTO read(Long idx);
    void modify(ListbookDTO dto);
    void remove(Long idx);

    default Listbook dtoToEntity(ListbookDTO dto){
        Listbook entity = Listbook.builder()
                .idx(dto.getIdx())
                .title(dto.getTitle())
                .detail(dto.getDetail())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .build();
        return entity;
    }



    default ListbookDTO entityToDTO(Listbook entity){
        ListbookDTO dto = ListbookDTO.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .detail(entity.getDetail())
                .author(entity.getAuthor())
                .price(entity.getPrice())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

    BooleanBuilder getSearch(PageRequestDTO requestDTO);


}
