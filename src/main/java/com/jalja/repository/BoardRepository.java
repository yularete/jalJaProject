package com.jalja.repository;

import com.jalja.domain.Board;
import com.jalja.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    //JpaRepository interface를 상속할 때에는 엔티티 타입과 @Id 타입을 지정해 줘야 한다.

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
