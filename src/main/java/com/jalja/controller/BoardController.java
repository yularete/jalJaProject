package com.jalja.controller;

import com.jalja.Service.BoardService;
import com.jalja.dto.BoardDTO;
import com.jalja.dto.PageRequestDTO;
import com.jalja.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

        return "board/list";
    }

    @GetMapping(value = "/test")
    public String list2(PageRequestDTO pageRequestDTO, Model model) {

        return "board/index";
    }
}
