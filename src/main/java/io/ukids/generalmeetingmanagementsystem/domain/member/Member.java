package io.ukids.generalmeetingmanagementsystem.domain.member;

import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String studentNumber;

    private String password;

    private String name;

    private String college;

    private String major;

    private boolean activate;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Authority> authorities;

    @Builder
    public Member(String studentNumber, String password, String name, String college, String major, Set<Authority> authorities) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.name = name;
        this.college = college;
        this.major = major;
        this.authorities = authorities;
        this.activate = true;
    }

    public void block() {
        if (!activate) {
            throw new IllegalStateException("이미 Block 된 유저입니다.");
        }
        activate = false;
    }

    public void validate(String name) {
        if (this.name.equals(name)) {
            throw new IllegalStateException("학번과 이름이 일치하지 않습니다.");
        }
    }
}
