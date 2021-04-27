package com.marchrich.findmyclass.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { // Posts, Long : Entity 클래스, PK 타입

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // SpringDataJpa에서 제공하지 않는 메소드는 위처럼 해결. 프로젝트시엔 이런 조건을 사용할 때 querydsl,joop,Mybatis등을 사용
    List<Posts> findAllDesc();

}

// 이전에 DAO로 불렸던것.
// Repository라 부르지만 @Repository추가 안해도됨
// 꼭 Entity클래스와 Entity Repository는 함께 위치해야함.