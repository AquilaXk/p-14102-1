package com.back.domain.post.post.service;


import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 게시물(Post) 엔티티 관련 비즈니스 로직을 담당하는 서비스 계층 클래스
 * 데이터 접근은 PostRepository에 위임하고, 핵심 비즈니스 기능을 구현
 */
@Service
@RequiredArgsConstructor
public class PostService {

    // 데이터베이스 접근을 담당하는 Repository 빈 의존성 주입
    private final PostRepository postRepository;

    /**
     * 현재 데이터베이스에 저장된 전체 게시물 수를 조회하는 메서드
     * @return 전체 Post 엔티티의 개수 반환 (long 타입)
     */
    public long count(){
        // Repository를 통해 DB 레코드 수 조회 기능을 위임.
        return postRepository.count();
    }

    /**
     * 새로운 게시물을 생성하고 데이터베이스에 저장하는 메서드
     * @param title 생성할 게시물의 제목
     * @param content 생성할 게시물의 내용
     * @return 데이터베이스에 저장된 Post 엔티티 객체 반환 (ID 및 자동 필드 포함)
     */
    public Post write(String title, String content){
        // 제목과 내용으로 새로운 Post 엔티티 객체 생성.
        Post post = new Post(title, content);

        // Repository를 통해 데이터베이스에 엔티티 저장 및 저장된 객체 반환.
        return postRepository.save(post);
    }
}