package com.mhj.base.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public List<MemberVO> getMembers() throws Exception;
	
	public int setJoin(MemberVO memberVO)throws Exception;
	
	public int setMemberRole(Map<String, Object> map)throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)throws Exception;
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception;
	
	public int setLastTime(MemberVO memberVO) throws Exception;
	
	public int setEnabled(MemberVO MemberVO) throws Exception;

	public int setLastTimeCheck(MemberVO memberVO) throws Exception;
	
	public List<MemberVO> getBirth() throws Exception;
	
	public MemberVO findPassword(MemberVO memberVO) throws Exception;
	
	public int setTempPassword(MemberVO memberVO) throws Exception;

}
