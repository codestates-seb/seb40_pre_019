package be.stackoverflow.user.repository;

import be.stackoverflow.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // 중복 회원가입인지 확인하는 용도
    Optional<User> findByUserName(String userName);

    // 회원을 조회하는 용도
    Optional<User> findByUserEmail(String userEmail);
}
