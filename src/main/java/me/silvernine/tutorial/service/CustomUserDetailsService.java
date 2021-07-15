package me.silvernine.tutorial.service;

import me.silvernine.tutorial.entity.User;
import me.silvernine.tutorial.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring Security 에서 중요한 부분중 하나인 UserDetailsService 를 구현한 CustomUserDetailsService 클래스
 * UserDetailsService 를 implements 하고 UserRepository 를 주입받습니다.
 * loadUserByUsername 메소드를 오버라이드 해서 로그인시에 DB 에서 유저정보와 권한정보를 가져오게 됩니다.
 * db에서 가져온 정보를 바탕으로 user 가 활성화 상태라면 그 유저의 권한정보들과 유저네임, 패스워드 정보를 기반으로
 * userdetails.User 객체를 생성해서 리턴합니다.
 */

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

   public CustomUserDetailsService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String username) {
      return userRepository.findOneWithAuthoritiesByUsername(username)
         .map(user -> createUser(username, user))
         .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   private org.springframework.security.core.userdetails.User createUser(String username, User user) {
      if (!user.isActivated()) {
         throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
      }
      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
              .collect(Collectors.toList());
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
              user.getPassword(),
              grantedAuthorities);
   }
}