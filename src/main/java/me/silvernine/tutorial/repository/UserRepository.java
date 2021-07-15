package me.silvernine.tutorial.repository;

import me.silvernine.tutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository 를 extends 하면 findAll, save 등의 메소드를 기본적으로 사용할 수 있게 됩니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {

   // @EntityGraph 은 쿼리가 수행이 될때 Lazy 조회가 아니고 Eager 조회로 authorities 정보를 같이 가져오게 됩니다.
   // username 을 기준으로 User 정보를 가져올때 권한 정보도 같이 가져오게 됩니다.
   @EntityGraph(attributePaths = "authorities")
   Optional<User> findOneWithAuthoritiesByUsername(String username);
}