package com.example.listbook.controller;

import com.example.listbook.dto.ListbookDTO;
import com.example.listbook.dto.PageRequestDTO;
import com.example.listbook.entity.Listbook;
import com.example.listbook.service.ListbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/listbook")
@Log4j2
@RequiredArgsConstructor

public class ListbookController {

    private final ListbookService service;


    @GetMapping("/")
    public String index(){
        return "redirect:/listbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list$$" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }


    @PostMapping("/register")
    public String registerPost(ListbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("등록, 목록 페이지 이동" + dto);
        Long idx = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", idx);
        return "redirect:/listbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long idx, @ModelAttribute("requestDTO")PageRequestDTO requestDTO, Model model){
        log.info("idx: " + idx);
        ListbookDTO dto = service.read(idx);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long idx, RedirectAttributes redirectAttributes){
        log.info("idx: " + idx );
        service.remove(idx);
        redirectAttributes.addFlashAttribute("msg", idx);
        return "redirect:/listbook/list";
    }

    @PostMapping("/modify")
    public String modify(ListbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("모디파이 전송");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("idx", dto.getIdx());

        return "redirect:/listbook/read";
    }
}
