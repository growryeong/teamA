package org.zerock.mapper;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.sf.log4jdbc.log.log4j2.Log4j2SpyLogDelegator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests2 {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testSearch() 
	{
		Criteria cri = new Criteria();
		cri.setKeyword("새로");
		cri.setType("TC");
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	@Test
	public void testLink()
	{
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
		        .queryParam("pageNum", 3)
		        .queryParam("amount", 20)
		        .queryParam("type","TC")
		        .queryParam("keyword", "새로");
		    
		 log.info("UriCom: "+builder.toUriString());
	}

}
