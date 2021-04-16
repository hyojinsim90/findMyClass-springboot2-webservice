package com.marchrich.findmyclass.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // Entity클래스에는 절대 setter를 만들지 않음. builder를 사용함
@NoArgsConstructor // 기본생성자. public Posts(){}와 같은 효과
@Entity // 테이블과 링크될 클래스라는 뜻. sales_manager테이블이면 salesManager.java 로 파일을 만듬
public class Posts { // 실제 DB테이블과 매칭된 클래스, Entity 클래스라고도 함. JPA를 사용하면 실제 쿼리를 날리는게 아니라 이 Entity 클래스의 수정을 통해 작업함

    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue : PK의 생성규칙. / GenerationType.IDENTITY : auto_increment
    private Long id; // 왠만하면 Entity의 PK는 Long타입의 Auto-increment를 추천

    @Column(length = 500, nullable = false) // Column을 안 사용해도 해당 클래스의 필드는 모드 칼럼을 뜻하지만 사용하는 이유는 기본값 외에 추가로 변경이 필요할때. varchar(255)가 기본인데 사이즈를 늘리고 싶다거나
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌드에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


}
