package com.ssg.homework.t2021hw.repository;

import com.ssg.homework.t2021hw.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
