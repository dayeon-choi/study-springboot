package com.dayeon.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /*

     * 단순히 인터페이스 생성 후, JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메서드 자동 생성
     * Entity 클래스와 기본 Repository는 함께 위치해야 함
     * Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음
     * 나중에 프로젝트 규모가 커져 도메인 별로 프로젝트를 분리해야 한다면, Entity 클래스와 기본 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리

     */

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
