package io.ukids.generalmeetingmanagementsystem.domain.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.ukids.generalmeetingmanagementsystem.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Member> findDynamicQueryMembers(MemberSearchCondition condition, Pageable pageable) {

        return jpaQueryFactory
                .selectFrom(member)
                .where(
                        eqCollege(condition.getCollege()),
                        eqMajor(condition.getMajor()),
                        eqActivate(condition.getActivate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression eqCollege(String searchCollege) {
        if (!StringUtils.hasText(searchCollege)) {
            return null;
        }
        return member.college.eq(searchCollege);
    }

    private BooleanExpression eqMajor(String searchMajor) {
        if (!StringUtils.hasText(searchMajor)) {
            return null;
        }
        return member.major.eq(searchMajor);
    }

    private BooleanExpression containsName(String searchName) {
        if (!StringUtils.hasText(searchName)) {
            return null;
        }
        return member.name.contains(searchName);
    }

    private BooleanExpression eqActivate(Boolean searchActivate) {
        if (searchActivate == null) {
            return null;
        }
        return member.activate.eq(searchActivate);
    }
}
