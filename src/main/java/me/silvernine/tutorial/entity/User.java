package me.silvernine.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * 설명: User
 * @Entity는 데이터베이스의 테이블과 1:1 매핑되는 객체를 뜻합니다.
 * @Table는 테이블명을 지정하기 위해 사용합니다.
 * @Getter, @Setter, @Builder, @...Constructor는 롬복 어노테이션으로 관련 코드를 자동으로 생성 : ※실무에서는 고려를 해야되는 점들이 조금 있으니 사용에 주의!
 */

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * @GeneratedValue(strategy = GenerationType.IDENTITY) 자동 증가되는 PK 어노테이션
     */

    @JsonIgnore
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    /**
     * 권한에 대한 관계
     * user -||--|< user_authority >|--||- authority
     * @ManyToMany,@JoinTable은 User 객체와 권한객체의 다대다 관계를 일대다, 다대일 관계의 조인 테이블로 정의했다는 뜻
     */
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}