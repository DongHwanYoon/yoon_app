package kr.ac.sunmoon.model.dto;



/* DTO(Data Transfer Object) 
 *  - 계층간 데이터 교환을 위한 자바빈즈
 *  - 보통 테이블의 컬럼들을 멤버변수로 처리
 *  - 캡슐화된 객체여야 함(멤버변수는 private 으로, public getter/setter 필수)
 *  - 로직을 가지지 않는 순수한 데이터 객체
 */

public class PortfolioData {
	private int no;
	private int portfolioNo;
	private String originalFileName;
	private String realFileName;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(int portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	
	

	public PortfolioData(String originalFileName, String realFileName) {
		super();
		this.originalFileName = originalFileName;
		this.realFileName = realFileName;
	}

	public PortfolioData(int portfolioNo, String originalFileName, String realFileName) {
		super();
		this.portfolioNo = portfolioNo;
		this.originalFileName = originalFileName;
		this.realFileName = realFileName;
	}

	public PortfolioData(int no, int portfolioNo, String originalFileName, String realFileName) {
		super();
		this.no = no;
		this.portfolioNo = portfolioNo;
		this.originalFileName = originalFileName;
		this.realFileName = realFileName;
	}

}
