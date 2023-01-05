package com.jalja.repository;

import com.jalja.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //JpaRepository interface를 상속할 때에는 엔티티 타입과 @Id 타입을 지정해 줘야 한다.
}
