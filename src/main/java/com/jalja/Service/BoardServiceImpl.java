package com.jalja.Service;

import com.jalja.domain.Board;
import com.jalja.dto.BoardDTO;
import com.jalja.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional //사용시 스프링은 해당 객체를 감싸는 별도의 클래스를 생성함
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO){
        //boardDto 인스턴스를 board.class(타겟클래스)에 매핑하여 board 객체 생성
        Board board = modelMapper.map(boardDTO, Board.class);

        Long bno = boardRepository.save(board).getBno();
        return bno;
    }
}
