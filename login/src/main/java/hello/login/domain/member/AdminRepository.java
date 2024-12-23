package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;


@Slf4j
@Repository
public class AdminRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L;//stacic 사용


    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream() //stream->> 반복문 같은 개념
                .filter(m->m.getLoginId().equals(loginId))
                .findFirst();
    }


    public List<Member> findAll() {
        return  new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }



}
