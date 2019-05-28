package com.infoaa.sharing.etracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoaa.sharing.etracker.model.Member;
import com.infoaa.sharing.etracker.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public void addMember(Member member){
		memberRepository.save(member);
	}

	public List<String> getFullName(){
		List<String> members = memberRepository.getMemberNames();
		if(members!=null){
			return members;
		}
		return null;
	}

	public int getMemberIdByName(String memberName){
		return memberRepository.getMemberIdByName(memberName);
	}

	public void deleteMember(String memberName){
		memberRepository.deleteById(getMemberIdByName(memberName));
	}
}
