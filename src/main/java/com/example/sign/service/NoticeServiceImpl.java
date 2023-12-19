package com.example.sign.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sign.dao.NoticeMapper;
import com.example.sign.model.NoticeModel;

@Service(value = "ContextService")
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper NoticeMapper;
	
	@Override
	public int TotalNoticeList() {
		return NoticeMapper.TotalNoticeList();
	}
	
	@Override
	public List<NoticeModel> noticeList() {
		return NoticeMapper.noticeList();
	}
	
	@Override
	public List<NoticeModel> noticeLists(int start, int end) {
		// 파라미터를 2개 이상 보낼떈 map을 사용한다.
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		return NoticeMapper.noticeLists(map);
	}
	
	@Override
	public Optional<NoticeModel> noticeListOne(int noticeNumber) {
		try {
			return NoticeMapper.noticeListOne(noticeNumber);			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public int noticeListViewCount(int noticeNumber) {
		return NoticeMapper.noticeListViewCount(noticeNumber);
	}
	
	@Override
	public void noticeInsert(NoticeModel noticeModel) {
		try {
			NoticeMapper.noticeInsert(noticeModel);			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void noticeUpdate(NoticeModel noticeModel) {
		try {
			NoticeMapper.noticeUpdate(noticeModel);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void noticeDelete(int noticeNumber) {
		try {
			NoticeMapper.noticeDelete(noticeNumber);			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}