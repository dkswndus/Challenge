package stuty.first.repository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import stuty.first.entity.UserEntity;


import java.util.Optional;


//JpaRepository는 Spring Data JPA 프레임워크에서 제공하는 인터페이스 중 하나
@SuppressWarnings("NullableProblems")
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //아이디 회원 정보 조회

    //Optional > null 방지
    Optional<UserEntity> findByUserId(String userId);



}