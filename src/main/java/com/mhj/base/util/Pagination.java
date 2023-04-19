package com.mhj.base.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
	
	//Page 번호를 담을 변수
	private Long page;
	
	public Long getPage() {
		if(this.page == null || this.page == 0) {
			this.page = 1L;
		}
		return this.page;
	}
	
	//한 Page에 보여줄 글의 개수
	private Long perPage;
	
	public Long getPerPage() {
		if(this.perPage == null || this.perPage < 1) {
			this.perPage = 10L;
		}
		return this.perPage;
	}
	
	//시작 index 번호
	private Long startRow;
	
	//시작 index 번호를 계산하는 메서드
	public void makeStartRow() {
		// page = 1, startRow = 0
		// page = 2, startRow = 10
		// page = 3, startRow = 20
		
		this.startRow = (this.getPage() - 1) * this.getPerPage();
	}
	
	//한 Block 당 출력할 Page의 개수
	private Long perBlock;
	
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock == 0) {
			this.perBlock = 5L;
		}
		return this.perBlock;
	}
	
	//시작 Page 번호
	private Long startNum;
	
	//끝 Page 번호
	private Long lastNum;
	
	//이전 Block 유무
	//false : 이전 X
	//true : 이전 O
	private boolean pre;
	//레퍼런스 타입이 아닌 프리미티브 타입으로 선언했기 때문에 초기값 null이 아닌 false
	
	//다음 Block 유무
	//false : 이전 X
	//true : 이전 O
	private boolean next;
	//레퍼런스 타입이 아닌 프리미티브 타입으로 선언했기 때문에 초기값 null이 아닌 false
	
	public void pagination(Long totalCount) {
		//1. 전체 글의 개수 받아오기
		//totalCount : 매개 변수 선언
		
		//2. 전체 글의 개수로 전체 페이지 개수 구하기
		Long totalPage = totalCount / this.getPerPage();
		if(totalCount % this.getPerPage() != 0) {
			totalPage++;
		}
		
		//3. 전체 페이지로 전체 블럭의 개수 구하기
		Long totalBlock = totalPage / this.getPerBlock();
		if(totalPage % this.getPerBlock() != 0) {
			totalBlock++;
		}
		
		//4. Page 번호로 현재 Block 번호 구하기
		Long block = this.getPage() / this.getPerBlock();
		if(this.getPage() % this.getPerBlock() != 0) {
			block++;
		}
		
		//5. 현재 Block 번호로 시작 번호와 끝 번호 구하기
		startNum = (block - 1) * this.getPerBlock() + 1;
		lastNum = block * this.getPerBlock();
		
		//6. 현재 Block 번호가 마지막 Block이라면 끝 번호는 전체 Page 번호와 같음
		if(block == totalBlock) {
			lastNum = totalPage;
		}
		
		//7. 이전 Block, 다음 Block 가능한지 유무
		if(block > 1) {
			this.pre = true;
		}
		
		if(totalBlock > block) {
			this.next = true;
		}
		
	}
	
	//검색할 종류
	private String condition;
	
	//검색어
	private String search;
	
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return this.search;
	}

}
