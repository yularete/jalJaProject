package com.jalja.Service;

import com.jalja.dto.BoardDTO;
import com.jalja.dto.PageRequestDTO;
import com.jalja.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        //BoardServiceImpl이 아닌 스프링에서 BoardServiceImpl을 감싸서 만든 클래스 정보가 출력됨.
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: "+ bno);
    }

    @Test
    public void testModify(){

        //변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Update....101")
                .content("Update content 101...")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }
}