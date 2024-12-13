package com.mbti.server.mbti.sign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignRepository  extends JpaRepository<Member, String> {
}