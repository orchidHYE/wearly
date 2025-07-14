package com.wearly.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDateTime;

@Data
@Table("MEMBER")
public class Member {
    @Id
    @Column("MEMBER_ID")
    private Long memberId;
    @Column("USERNAME")
    private String username;
    @Column("EMAIL")
    private String email;
    @Column("PASSWORD")
    private String password;
    @Column("MEMBER_TYPE")
    private String memberType; // CURATOR, REQUESTER
    @Column("PROFILE_IMAGE")
    private String profileImage;
    @Column("NICKNAME")
    private String nickname;
    @Column("BIO")
    private String bio;
    // 큐레이터 전용
    @Column("SNS_LINK")
    private String snsLink;
    @Column("IS_VERIFIED")
    private Boolean isVerified;
    @Column("RATING")
    private Double rating;
    // 리퀘스터 전용
    @Column("STYLE_PREFERENCE")
    private String stylePreference;
    @Column("CREATED_AT")
    private LocalDateTime createdAt;
    @Column("UPDATED_AT")
    private LocalDateTime updatedAt;
}