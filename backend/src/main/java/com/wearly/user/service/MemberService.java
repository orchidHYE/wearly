package com.wearly.user.service;

import com.wearly.user.model.Member;
import com.wearly.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member createMember(Member member) {
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member memberDetails) {
        return memberRepository.findById(id)
                .map(existingMember -> {
                    existingMember.setUsername(memberDetails.getUsername());
                    existingMember.setEmail(memberDetails.getEmail());
                    existingMember.setPassword(memberDetails.getPassword());
                    existingMember.setProfileImage(memberDetails.getProfileImage());
                    existingMember.setNickname(memberDetails.getNickname());
                    existingMember.setBio(memberDetails.getBio());
                    existingMember.setSnsLink(memberDetails.getSnsLink());
                    existingMember.setIsVerified(memberDetails.getIsVerified());
                    existingMember.setRating(memberDetails.getRating());
                    existingMember.setStylePreference(memberDetails.getStylePreference());
                    existingMember.setMemberType(memberDetails.getMemberType());
                    existingMember.setUpdatedAt(LocalDateTime.now());
                    return memberRepository.save(existingMember);
                })
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public List<Member> getAllCurators() {
        return memberRepository.findAllCurators();
    }

    public List<Member> getAllRequesters() {
        return memberRepository.findAllRequesters();
    }
} 