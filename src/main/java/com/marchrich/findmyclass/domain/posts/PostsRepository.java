package com.marchrich.findmyclass.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // Posts, Long : Entity 클래스, PK 타입
}

// 이전에 DAO로 불렸던것.
// Repository라 부르지만 @Repository추가 안해도됨
// 꼭 Entity클래스와 Entity Repository는 함께 위치해야함.