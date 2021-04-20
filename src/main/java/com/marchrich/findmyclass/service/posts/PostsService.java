package com.marchrich.findmyclass.service.posts;

import com.marchrich.findmyclass.domain.posts.Posts;
import com.marchrich.findmyclass.domain.posts.PostsRepository;
import com.marchrich.findmyclass.web.dto.PostsResponseDto;
import com.marchrich.findmyclass.web.dto.PostsSaveRequestDto;
import com.marchrich.findmyclass.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

        @Transactional
        public Long save(PostsSaveRequestDto requestDto) {
            return postsRepository.save(requestDto.toEntity()).getId();
        }

        @Transactional
        public Long update(Long id, PostsUpdateRequestDto requestDto){
            Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

            posts.update(requestDto.getTitle(), requestDto.getContent());

            return id;
        }
        // 더티체킹 : update Transaction이 끝나는 시점에 해당 테이블에 변경분을 반영. update 쿼리문 날릴 필요 없음.


        public PostsResponseDto findById(Long id) {
            Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

            return new PostsResponseDto(entity);
        }

}
