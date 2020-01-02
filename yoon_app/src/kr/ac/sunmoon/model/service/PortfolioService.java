package kr.ac.sunmoon.model.service;

import java.util.ArrayList;

import kr.ac.sunmoon.model.dao.PortfolioDAO;
import kr.ac.sunmoon.model.dto.Portfolio;
import kr.ac.sunmoon.model.dto.PortfolioData;

public class PortfolioService {
	
	private PortfolioDAO pDao = new PortfolioDAO();
	
	// 포트폴리오 등록하는 메소드. 트랜잭션 적용 필요하지만 하지는 않은 상태. 첨부파일이 등록되지 않은 채 포트폴리오가 올라갈 수도 있음.
	public void register(Portfolio p) throws Exception {
		int no = pDao.insert(p);
		ArrayList<PortfolioData> list = p.getDataList();
		
		if (list != null && list.size() > 0) {
			pDao.insert(no, p.getDataList());
		}
	}
	
	// 포트폴리오 전체 리스트를 가져오는 메소드. 리스트를 그냥 리턴만 해줌
	public ArrayList<Portfolio> getList() throws Exception {
		return pDao.select();
	}
	
	public ArrayList<Portfolio> search(String title) throws Exception {
		return pDao.select(title);
	}
	
	public Portfolio getDetail(int no) throws Exception {
		Portfolio p = pDao.select(no);
		p.setDataList(pDao.selectDataList(no));
		return p;
	}
	
	// 포트폴리오 수정하는 메소드(기본정보 수정만)
	public void modify(Portfolio p) throws Exception {
		pDao.update(p);
	}
	
	// 포트폴리오를 삭제하는 메소드
	public void remove(int no) throws Exception {
		pDao.delete(no);
	}
}
