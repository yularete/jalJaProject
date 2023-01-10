package com.jalja.Service;

import com.jalja.dto.BoardDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO); //등록 작업 처리

    BoardDTO readOne(Long bno);
}
