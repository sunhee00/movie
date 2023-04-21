package model;

public class Paging {
	//한번에 표기되는 페이지 수 [이전] 1 2 3 4 5 [이후]
	private int page=5;
	//표기되는 페이지 중 첫번째 : 1
	private int blockStart;
	//표기되는 페이지 중 마지막 : 5
	private int blockEnd;
	//전체 페이지 수 (전체 게시글 수가 31개면 게시물은 5개씩 페이징되니 총 7페이지)
	private int pageCount;
	
	public int getPage() {
		return page;
	}
	public int getBlockStart() {
		return blockStart;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public int getPageCount() {
		return pageCount;
	}
	
	//총페이지 수
	public void getAllPage(int count, int pageSize) {
		if(count%pageSize == 0) {//페이지가 한페이지당 최대 게시글 개수로 나누어서 딱 나누어 떨어지면
			pageCount = count/pageSize; //전체 게시글 개수/페이지에 들어갈수 있는 최대 게시글 개수가 총 페이지 개수가 됨
		}else {
			pageCount = (count/pageSize)+1;
		}
		
	}
	
	//페이지 블록
	public void getPageBlock(int curPage) {
		//페이지가 5의 배수(paging)가 아닌 것들
		if(curPage%page != 0) {
			blockStart = (curPage/page)*page+1;
		}else {//페이지가 5의 배수인 것들
			blockStart = curPage-(page-1);
		}
		blockEnd = (blockStart + page) -1;
		if(blockEnd > pageCount) {//총페이지 수보다 많으면 가장 끝 페이지로 blockEnd가 정해짐 (위의 식 때문에)
			blockEnd = pageCount;
		}
		
	}
}
