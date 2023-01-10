package com.jalja.Service;

import com.jalja.dto.BoardDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO); //등록 작업 처리

    BoardDTO readOne(Long bno); //조회 작업 처리는 특정 게시물 번호를 이용

    void modify(BoardDTO boardDTO); //기존의 엔티티 객체에서 필요한 부분만 변경하도록 함

    void remove(Long bno);
}
