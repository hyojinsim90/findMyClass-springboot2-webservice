package com.marchrich.findmyclass.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // 단위테스트 끝날 때 수행되는 메소드. 보통은 배포 전 전체 테스트를 수행할 때 데이터 침범을 막기위해 사용. 여러 테스트가 동시에 실행되면 테스트용 데이터베이스 H2에 데이터가 그대로남아 테스트 실패 가능성 있음
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());
    /*
    * postsRepository.save 
    * 테이블 posts에 insert/update쿼리 실행
    * id값이 있다면 update, 없다면 insert 쿼리 실행
    * */

    // when
    List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터 조회

    // then
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);

    }
}
