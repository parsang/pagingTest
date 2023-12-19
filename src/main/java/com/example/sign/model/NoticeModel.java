package com.example.sign.model;

public class NoticeModel{
	private int noticeNumber;
	private String memberId;
	private String noticeTitle;
	private String noticeContext;
	private String noticeImg;
	private String noticeWriteTime;
	private String noticeUpdateTime;
	private int noticeView;
	
	public int getNoticeNumber() {
		return noticeNumber;
	}
	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public String getNoticeContext() {
		return noticeContext;
	}
	public void setNoticeContext(String noticeContext) {
		this.noticeContext = noticeContext;
	}
	
	public String getNoticeImg() {
		return noticeImg;
	}
	public void setNoticeImg(String noticeImg) {
		this.noticeImg = noticeImg;
	}
	
	public String getNoticeWriteTime() {
		return noticeWriteTime;
	}
	public void setNoticeWriteTime(String noticeWriteTime) {
		this.noticeWriteTime = noticeWriteTime;
	}
	
	public String getNoticeUpdateTime() {
		return noticeUpdateTime;
	}
	public void setNoticeUpdateTime(String noticeUpdateTime) {
		this.noticeUpdateTime = noticeUpdateTime;
	}
	
	public int getNoticeView() {
		return noticeView;
	}
	public void setNoticeView(int noticeView) {
		this.noticeView = noticeView;
	}
}