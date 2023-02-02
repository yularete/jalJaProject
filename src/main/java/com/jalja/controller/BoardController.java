package com.jalja.controller;

import com.jalja.Service.BoardService;
import com.jalja.dto.BoardDTO;
import com.jalja.dto.PageRequestDTO;
import com.jalja.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @GetMapping("/register") //등록 처리는 get으로 화면을 보고 post로 처리한다.
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("board POST register....");

        //@Valid에서 문제가 발생했을 때 모든 에러를 'errors'라는 이름으로 RedirectAttributes에 추가해서 전송
        if(bindingResult.hasErrors()){
            log.info("has errors....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }
        log.info(boardDTO);

        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping(value = "/test")
    public String list2(PageRequestDTO pageRequestDTO, Model model) {

        return "board/index";
    }
}
