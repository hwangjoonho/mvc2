package hello.jdbc.repository;

import hello.jdbc.domain.Member;

public interface MemberRepository {

    /**
     * interface 에서는 checkedException을 처리해줘야함.
     * V4_1 버전에서의 RuntimeException 작업으로 인해 깔끔해짐
     */
    Member save(Member member);

    Member findById(String memberId);

    void update(String memberId, int money);

    void delete(String memberId);

}
