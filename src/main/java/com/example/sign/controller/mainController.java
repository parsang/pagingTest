package com.example.sign.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.sign.model.NoticeModel;
import com.example.sign.model.pagingModel;
import com.example.sign.service.NoticeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class mainController {
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private pagingModel pagingModel;
	
	public void setPagingModel(pagingModel pagingModel) {
		this.pagingModel = pagingModel;
	}
	
	// 페이지 접속시 /home 경로로 이동
//	@RequestMapping("/")
//	public String home() {
//	    return "redirect:/home";
//	}

//  @RequestMapping : method 속성을 생략하면 GET, POST, PUT, DELETE 등 모든 HTTP 메서드에 대한 요청을 처리
//  @GetMapping, @PostMapping등을 사용하는 이유는 메서드의 용도가 명시적으로 드러나기 때문에 코드의 가독성이 좋아진다.
//  의도치 않게 GET 대신 POST를 사용하거나 그 반대의 경우 에러발생
	
//	@GetMapping("/home")
//	public ModelAndView goHome(HttpServletRequest request) {
//		
//		// 데이터 담는 바구니
//		ModelAndView mv = new ModelAndView();
//		
//		// DB데이터 변수
//		List<ShopModel> memberList = shopService.memberList();
//		System.out.println("홈페이지");
//		System.out.println("유저 수는 : " + memberList.size() + "명 입니다"); 
//		
//		mv.addObject("memberList", memberList);
//		mv.setViewName("content/home");
//		
//		return mv;
//	}
	
	// 게시글 리스트 이동
	@GetMapping("/noticeList")
	public ModelAndView goNotice(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		List<NoticeModel> noticeList = noticeService.noticeList();
		System.out.println("게시판 페이지 개수 : " + noticeList.size());
		
		mv.addObject("noticeList", noticeList);
		mv.setViewName("content/noticeList");
		
		return mv;
	}
	
	// 게시글 리스트 이동 pagging기법 사용
	@GetMapping("/noticeLists/{pageNumber}")
	// 메서드 파라미터의 이름이 컴파일 시에 지워진다.
	// 그 해결법을 위해 @PathVariable(파라미터 이름)을 한다.
	public ModelAndView goNoticeList(@PathVariable("pageNumber") int pageNumber) {
		ModelAndView mv = new ModelAndView();
		
		// 게시글 총 개수 및 페이지 총 개수 구하기
		int NoticeListCount = noticeService.TotalNoticeList();
		pagingModel.setTotalRecord(NoticeListCount);
		
		// 게시글 총 개수 <= 페이지당 보여주는 싶은 게시글 수라면
		if(pagingModel.getTotalRecord() <= pagingModel.getNumPerPage()) {
			// 페이지는 1페이지만 만들어주세요
			pagingModel.setTotalPage(1);
		}else {
			// 거짓이면 (게시글 총 개수 / 페이지당 보여주는 싶은 게시글 수) 개수만큼 페이지를 만들어 주세요
			pagingModel.setTotalPage(pagingModel.getTotalRecord() / pagingModel.getNumPerPage());
			
			// 만약 (게시글 총 개수 / 페이지당 보여주는 싶은 게시글 수)의 나머지 값이 있다면
			if(pagingModel.getTotalRecord() % pagingModel.getNumPerPage() != 0) {
				// 페이지 개수를 1개 더 만들어주세요
				pagingModel.setTotalPage(pagingModel.getTotalPage() + 1);
			}
		}
		
		// 페이지 좌표 구하기
		// pageNumber가 0보다 작거나 같은 경우에
		if(pageNumber <= 0) {
			// 현재 페이지를 1번 페이지로 해주세요
			pagingModel.setNowPage(1);
		}else {
			// 아니라면 유저가 선택한 페이지의 값을 가져와 현재 페이지로 해주세요
			pagingModel.setNowPage(pageNumber);
		}
		
		// 페이지 리스트 수 구하기
		// 페이지 첫번째 게시글 번호 = (현재 페이지 - 1) * (페이지당 보여주는 싶은 게시글 수 + 1)
		pagingModel.setStart((pagingModel.getNowPage() - 1) * pagingModel.getNumPerPage() + 1);
		
		// 페이지 마지막 게시글 번호 = (페이지 첫번째 게시글 번호 - 1) * (페이지당 보여주는 싶은 게시글 수 + 1)
		pagingModel.setEnd((pagingModel.getStart() - 1) + pagingModel.getNumPerPage());
		
		// 첫번째 페이지 번호 = ((현재 페이지 - 1) / 한번에 보여줄 페이지 수) * (한번에 보여줄 페이지 수 + 1)
		pagingModel.setStartBlock(
				(int) ((pagingModel.getNowPage() - 1) / pagingModel.getPagePerBlock())
				* pagingModel.getPagePerBlock() + 1);
		
		// 마지막 페이지 번호 = 첫번째 페이지 번호 + 한번에 보여줄 페이지 수 - 1
		pagingModel.setEndBlock(pagingModel.getStartBlock() + pagingModel.getPagePerBlock() - 1);
		
		// 만약 마지막 페이지 번호가 총 페이지 수보다 크다면
		if(pagingModel.getEndBlock() > pagingModel.getTotalPage()) {
			// 마지막 페이지 번호는 총 페이지 수로 해주세요
			pagingModel.setEndBlock(pagingModel.getTotalPage());
		}
		
		List<NoticeModel> noticeLists = noticeService.noticeLists(pagingModel.getStart(), pagingModel.getEnd());
	
		System.out.println("게시판 게시글 개수 : " + pagingModel.getTotalRecord());
		System.out.println("게시판 페이지 개수 : " + pagingModel.getTotalPage());
		System.out.println("페이지 시작 게시글 번호 : " + pagingModel.getStart());
		System.out.println("페이지 끝 게시글 번호 : " + pagingModel.getEnd());
		System.out.println("게시판 시작 페이지 번호 : " + pagingModel.getStartBlock());
		System.out.println("게시판 끝 페이지 번호 : " + pagingModel.getEndBlock());
		
		
		mv.addObject("noticeLists", noticeLists);
		mv.addObject("pagingModel", pagingModel);
		mv.setViewName("content/noticeLists");
		return mv;
	}
	
	// 게시글 상세 페이지 이동
	@GetMapping("/notice/{noticeNumber}")
	public ModelAndView goNoticeDetail(@PathVariable("noticeNumber") int noticeNumber) {
	    ModelAndView mv = new ModelAndView();
	    
	    try {
	    	Optional<NoticeModel> noticeListOne = noticeService.noticeListOne(noticeNumber);

	    	if (noticeListOne.isPresent()) {
	    		// 조회수 + 1 하기
	    		int viewCount = noticeService.noticeListViewCount(noticeNumber);
	    		
	    		mv.addObject("noticeListOne", noticeListOne.get());
	    		mv.setViewName("content/noticeDetail");
	    		return mv;				
			} else {
				// 상세페이지가 없는경우
				mv.setViewName("content/error");
	    		return mv;
			}
	    } catch (Exception e) {
	        System.out.println(e);
	        mv.setViewName("content/error");
		    return mv;
	    }
	}
	
	// 게시글 작성 페이지 이동
	@GetMapping("/notice/write")
	public ModelAndView goNoticeWrite(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		// 현재 시간을 가져와서 포맷팅
		LocalDateTime Time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentTime = Time.format(formatter);
		
		// 현재 시간을 추가
		mv.addObject("currentTime", currentTime);
				
		// 글작성 시 데이터를 담을 박스 추가
		mv.addObject("noticeModel", new NoticeModel());
		
		mv.setViewName("content/noticeWrite");
		
		return mv;
	}
	
	// 게시글 작성 작업
	@PostMapping("/notice/write/insert")
	public String goNoticeWriteCreate(@ModelAttribute("noticeModel") NoticeModel noticeModel) {
		try {
			noticeService.noticeInsert(noticeModel);
			return "redirect:/noticeLists/1";
		} catch (Exception e) {
			System.out.println(e);
			return "content/error";
		}
	}
	
	// 게시글 수정 페이지 이동
	@GetMapping("/noticeUpdate/{noticeNumber}")
	public ModelAndView goNoticeUpdate(@PathVariable("noticeNumber") int noticeNumber) {
	    ModelAndView mv = new ModelAndView();
	    
		 // 현재 시간을 가져와서 포맷팅
		 LocalDateTime Time = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String currentTime = Time.format(formatter);
		 
		 // 현재 시간을 추가
		 mv.addObject("currentTime", currentTime);
	    
	    try {
	    	Optional<NoticeModel> noticeListOne = noticeService.noticeListOne(noticeNumber);
	    	if (noticeListOne.isPresent()) {
	    		mv.addObject("noticeListOne", noticeListOne.get());
	    		mv.setViewName("content/noticeUpdate");
	    		return mv;				
			} else {
				mv.setViewName("content/error");
	    		return mv;
			}
	    } catch (Exception e) {
	        System.out.println(e);
	        mv.setViewName("content/error");
		    return mv;
	    }
	}

	// 게시글 수정 작업
	@PostMapping("/notice/write/update")
	public String goNoticeUpdate(@ModelAttribute("noticeModel") NoticeModel noticeModel) {
		try {
			noticeService.noticeUpdate(noticeModel);
			System.out.println("업데이트 확인");
			System.out.println(noticeModel.getNoticeNumber());
			return "redirect:/noticeLists/1";
		} catch (Exception e) {
			System.out.println(e);
			return "content/error";
		}
	}
	
	// 게시글 삭제 작업
	@GetMapping("/noticeDelete/{noticeNumber}")
	public String goNoticeDelete(@PathVariable("noticeNumber") int noticeNumber) {
	    try {
			noticeService.noticeDelete(noticeNumber);
			return "redirect:/noticeLists/1";
		} catch (Exception e) {
			System.out.println(e);
			return "content/error";
		}
	}
}
