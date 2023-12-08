package com.ll.medium.domain.home.home.controller.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> { //Member entitiy를 상속받아 CRUD 작업 수행
    Optional<Member> findByusername(String username);
    //Member username 조회, 해당 하는 username이 있으면 Optional 객체에 담겨 반환, 없으면, Optional.empty() 반환
    //null 값을 처리하기 위해 Optional 사용
}
