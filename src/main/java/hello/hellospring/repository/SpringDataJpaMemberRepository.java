package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // <T, ID> ID는 PK

  // 스프링 Data JPA 가 구현체를 만들어 빈에 자동 등록함.

  // select m form Member m where m.name = ?
  @Override
  Optional<Member> findByName(String name);

}
