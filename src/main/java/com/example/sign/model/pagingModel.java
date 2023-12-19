package com.example.sign.model;

import org.springframework.stereotype.Service;

@Service
public class pagingModel {
	private int nowPage = 1;      // 현재 페이지
	private int nowBlock = 1;     // 현재 묶음 페이지?
	
	// 직접 숫자를 조정하기
	private int numPerPage = 10;  // 페이지당 보여주는 싶은 게시글 수
	private int pagePerBlock = 5; // 한번에 보여줄 페이지 수
	
	private int totalRecord = 0;  // 총 게시글 수
	private int totalPage = 0;    // 총 페이지 수
	private int totalBlock = 0;   // 총 묶음 페이지 수
	
	private int start = 0;        // 페이지 시작 게시글 번호
	private int end = 0;          // 페이지 끝 게시글 번호
	
	private int startBlock = 0;   // 게시판 시작 페이지 번호
	private int endBlock = 0;     // 게시판 끝 페이지 번호
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public int getNowBlock() {
		return nowBlock;
	}
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getStartBlock() {
		return startBlock;
	}
	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}
	
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}
}
