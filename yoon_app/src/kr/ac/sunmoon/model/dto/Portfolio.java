package kr.ac.sunmoon.model.dto;

import java.util.ArrayList;
import java.util.StringTokenizer;


/* DTO(Data Transfer Object) 
 *  - 계층간 데이터 교환을 위한 자바빈즈
 *  - 보통 테이블의 컬럼들을 멤버변수로 처리
 *  - 캡슐화된 객체여야 함(멤버변수는 private 으로, public getter/setter 필수)
 *  - 로직을 가지지 않는 순수한 데이터 객체
 */

public class Portfolio {
	private int no;
	private String title; // 제목
	private String leader; // 대표자
	private String member; // 참여자
	private String content; // 내용
	private String startDate; // 시작일
	private String endDate; // 종료일
	private String regDate; // 등록일
	private ArrayList<PortfolioData> dataList; // 하나의 포트폴리오가 여러개의 data를 가짐. 일반 배열과 다르게 크기가 가변적임. 1:n 관계
	private int dataCount; 
	private int memberCount;

	
	// 포트폴리오를 가져올 때 사용
	public Portfolio(int no, String title, String leader, String member, String content, String startDate,
			String endDate, String regDate, int dataCount) {
		this(title, leader, member, content, startDate, endDate);
		this.no = no;
		this.regDate = regDate;
		this.dataCount = dataCount;
	}
	
	// 
	public Portfolio(String title, String leader, String member, String content, String startDate, String endDate) {
		super();
		this.title = title;
		this.leader = leader;
		this.member = member;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		StringTokenizer st = new StringTokenizer(member, ",");
		this.memberCount = st.countTokens();
	}
	
	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public ArrayList<PortfolioData> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<PortfolioData> dataList) {
		this.dataList = dataList;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	
	public int memberCount() {
		return memberCount;
	}

	public void setmemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
	
}
