package com.example.sign.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.sign.model.NoticeModel;

@Repository
@Mapper
public interface NoticeMapper {
	int TotalNoticeList();
	
	List<NoticeModel> noticeList();
	
	List<NoticeModel> noticeLists(Map<String, Integer> map);
	
	Optional<NoticeModel> noticeListOne(int noticeNumber);
	
	int noticeListViewCount(int noticeNumber);

	void noticeInsert(NoticeModel noticeModel);

	void noticeUpdate(NoticeModel noticeModel);

	void noticeDelete(int noticeNumber);
}