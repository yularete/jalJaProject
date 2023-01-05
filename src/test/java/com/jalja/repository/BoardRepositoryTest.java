package com.jalja.repository;

import com.jalja.domain.Board;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.stream.IntStream;

//@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testBoardInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..."+i)
                    .content("content..."+i)
                    .writer("user"+(i%10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testBoardSelect(){
        Long bno = 100L;
        //findById()의 리턴 타입은 Optional<T> !!
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void testBoardUpdate(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        board.modify("modify..title 100", "modify content 100");

        boardRepository.save(board);
    }

    @Test
    public void testBoardDelete(){
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }
}