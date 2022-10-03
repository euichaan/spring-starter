package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) { // 생성자 주입
    this.memberRepository = memberRepository;
  }

  /**
   * 회원 가입
   */
  public Long join(Member member) {
    // 같은 이름이 있는 중복 회원X
    validateDuplicateMember(member); // 중복 회원 검증

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName()) // 이 결과는 Optional<Member>
        .ifPresent(m -> { // null 이 아니라 값이 있다면
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /**
   * 단일 회원 조회
   */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}