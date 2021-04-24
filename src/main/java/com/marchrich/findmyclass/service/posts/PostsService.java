package com.marchrich.findmyclass.service.posts;

import com.marchrich.findmyclass.domain.posts.Posts;
import com.marchrich.findmyclass.domain.posts.PostsRepository;
import com.marchrich.findmyclass.web.dto.PostsListResponseDto;
import com.marchrich.findmyclass.web.dto.PostsResponseDto;
import com.marchrich.findmyclass.web.dto.PostsSaveRequestDto;
import com.marchrich.findmyclass.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

        @Transactional
        public void delete(Long id){
            Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
            // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 삭제
            
            postsRepository.delete(posts);
            // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할수도있다.
        }

        public PostsResponseDto findById(Long id) {
            Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

            return new PostsResponseDto(entity);
        }

        @Transactional(readOnly = true) // readOnly : 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선
        public List<PostsListResponseDto> findAllDesc() {
            return postsRepository.findAllDesc().stream()
                    .map(PostsListResponseDto::new) // = .map(posts -> new PostsListResponseDto(posts))
                    .collect(Collectors.toList());
            // postsRepository로 넘어온 Posts의 Stream을 map을 통해 PostsListsResponseDto로 변환 -> List로 반환
        }
}
