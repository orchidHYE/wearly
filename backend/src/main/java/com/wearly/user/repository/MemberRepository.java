package com.wearly.user.repository;

import com.wearly.user.model.Member;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    
    @Query("SELECT * FROM MEMBER WHERE USERNAME = :username")
    Optional<Member> findByUsername(@Param("username") String username);
    
    @Query("SELECT * FROM MEMBER WHERE EMAIL = :email")
    Optional<Member> findByEmail(@Param("email") String email);
    
    @Query("SELECT * FROM MEMBER WHERE USERNAME = :username AND EMAIL = :email")
    Optional<Member> findByUsernameAndEmail(@Param("username") String username, @Param("email") String email);

    @Query("SELECT * FROM MEMBER WHERE MEMBER_TYPE = 'CURATOR'")
    List<Member> findAllCurators();

    @Query("SELECT * FROM MEMBER WHERE MEMBER_TYPE = 'REQUESTER'")
    List<Member> findAllRequesters();
} 