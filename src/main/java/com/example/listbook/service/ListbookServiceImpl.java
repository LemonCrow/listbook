package com.example.listbook.service;

import com.example.listbook.dto.ListbookDTO;
import com.example.listbook.dto.PageRequestDTO;
import com.example.listbook.dto.PageResultDTO;
import com.example.listbook.entity.Listbook;
import com.example.listbook.entity.QListbook;
import com.example.listbook.repository.ListbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ListbookServiceImpl implements ListbookService{

    private final ListbookRepository repository;


    @Override
    public Long register(ListbookDTO dto){
        Listbook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getIdx();
    }

    @Override
    public PageResultDTO<ListbookDTO, Listbook> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("idx").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Listbook> result = repository.findAll(booleanBuilder, pageable);
        Function<Listbook, ListbookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ListbookDTO read(Long idx){
        Optional<Listbook> result = repository.findById(idx);
        return result.isPresent() ? entityToDTO(result.get()): null;
    }

    @Override
    public void modify(ListbookDTO dto) {
        Optional<Listbook> result = repository.findById(dto.getIdx());
        if(result.isPresent()){
            Listbook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeAuthor(dto.getAuthor());
            entity.changeDetail(dto.getDetail());
            entity.changePrice(dto.getPrice());

            repository.save(entity);
        }
    }

    @Override
    public void remove(Long idx) {repository.deleteById(idx);}


    @Override
    public BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keword = requestDTO.getKeyword();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QListbook qListbook = QListbook.listbook;
        BooleanExpression expression = qListbook.idx.gt(0L); //idx > 0

        booleanBuilder.and(expression);

        //검색 형식이 선택되어 있지 않은 경우
        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qListbook.title.contains(keword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qListbook.detail.contains(keword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qListbook.author.contains(keword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }


}
