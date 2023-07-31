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
    private Long id;

    private String studentNumber;

    private String password;

    private String name;

    private String college;

    private String major;

    private Integer grade;

    private String imageUrl;

    private boolean activate;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Authority> authorities;

    @Builder
    public Member(String studentNumber, String password, String name, String college, String major, Integer grade, String imageUrl, Set<Authority> authorities) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.name = name;
        this.college = college;
        this.major = major;
        this.grade = grade;
        this.imageUrl = imageUrl;
        this.authorities = authorities;
        this.activate = false;
    }

    public void permit() {
        if (activate) {
            throw new IllegalStateException("이미 활성화된 유저입니다.");
        }
        activate = true;
    }

    public void block() {
        if (!activate) {
            throw new IllegalStateException("이미 Block 된 유저입니다.");
        }
        activate = false;
    }
}
