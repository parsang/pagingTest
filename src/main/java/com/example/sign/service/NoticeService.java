package com.example.sign.service;

import java.util.List;
import java.util.Optional;

import com.example.sign.model.NoticeModel;

public interface NoticeService {
	// 게시글 수
	public int TotalNoticeList();
	
	// 게시글 리스트 보기
	public List<NoticeModel> noticeList();
	
	// 게시글 리스트 보기 페이징기법
	public List<NoticeModel> noticeLists(int start, int end);
	
	// 게시글 상세보기
	public Optional<NoticeModel> noticeListOne(int noticeNumber);
	
	// 게시글 조회수 증가
	public int noticeListViewCount(int noticeNumber);
	
	// 게시글 추가
	public void noticeInsert(NoticeModel noticeModel);
	
	// 게시글 수정
	public void noticeUpdate(NoticeModel noticeModel);
	
	// 게시글 삭제
	public void noticeDelete(int noticeNumber);
}