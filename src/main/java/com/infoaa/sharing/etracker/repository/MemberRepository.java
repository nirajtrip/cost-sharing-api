package com.infoaa.sharing.etracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infoaa.sharing.etracker.model.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

	@Query("SELECT e.fullName FROM Member e")
	public List<String> getMemberNames();

	@Query("SELECT e.memberId FROM Member e WHERE e.fullName = :memberName")
	public int getMemberIdByName(@Param("memberName")String memberName);
}
