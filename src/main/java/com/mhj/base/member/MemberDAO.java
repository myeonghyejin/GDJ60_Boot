package com.mhj.base.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	/** SELECT **/
	public List<MemberVO> getList() throws Exception;
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	/** INSERT **/
	public int setJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRole(MemberVO memberVO) throws Exception;

}
