package com.wearly.user.controller;

import com.wearly.user.model.Member;
import com.wearly.user.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원(큐레이터/리퀘스터) 관리 API")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @Operation(summary = "모든 회원 조회", description = "등록된 모든 회원 목록을 조회합니다.")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "회원 조회", description = "특정 회원 정보를 조회합니다.")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다.")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    @PutMapping("/{id}")
    @Operation(summary = "회원 정보 수정", description = "기존 회원 정보를 수정합니다.")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/curators")
    @Operation(summary = "모든 큐레이터 조회", description = "member_type이 'CURATOR'인 회원만 조회합니다.")
    public ResponseEntity<List<Member>> getAllCurators() {
        return ResponseEntity.ok(memberService.getAllCurators());
    }

    @GetMapping("/requesters")
    @Operation(summary = "모든 리퀘스터 조회", description = "member_type이 'REQUESTER'인 회원만 조회합니다.")
    public ResponseEntity<List<Member>> getAllRequesters() {
        return ResponseEntity.ok(memberService.getAllRequesters());
    }
} 