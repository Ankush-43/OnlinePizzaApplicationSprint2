package com.cg.onlinepizza.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.Exceptions.InvalidCoupanOperationException;
import com.cg.onlinepizza.dao.ICouponRepositoryDao;
import com.cg.onlinepizza.model.Coupan;
import com.cg.onlinepizza.service.ICoupanServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ICoupanServiceTests {

	@Autowired
	private ICoupanServiceImpl service;
	@MockBean
	private ICouponRepositoryDao dao;
	
	@Test
	public void viewCoupansTest() {
		when(dao.findAll()).thenReturn(Stream.of(new Coupan(100, "abc", "xyz", (float) 100.25)).collect(Collectors.toList()));
		assertEquals(1, service.viewCoupans().size());
	}
	
	@Test
	public void addCoupansTest() {
		Coupan coupan= new Coupan(101, "abc", "akl", (float) 200.78);
		when(dao.saveAndFlush(coupan)).thenReturn(coupan);
		assertEquals(coupan, dao.saveAndFlush(coupan));
	}
 
	@Test
	public void deleteCoupansTest() throws CoupanIdNotFoundException  {
		when(dao.existsById(100)).thenReturn(true);
		Mockito.when(service.deleteCoupans(100)).thenReturn("Deleted Coupon");
		}
	
	@Test
	public void editCoupansTest(){
		Coupan coupan= new Coupan(101, "abc", "akl", (float) 200.0);
		when(dao.existsById(coupan.getCoupanId())).thenReturn(true);
		coupan.setCoupanId(101);
		coupan.setCoupanCode("xyz");
		coupan.setCoupanDescription("akl");
		coupan.setCoupanDiscount(200);
		when(dao.existsById(coupan.getCoupanId())).thenReturn(true);
		when(service.editCoupans(coupan)).thenReturn(coupan);
		assertEquals("xyz", coupan.getCoupanCode());
		}
}
