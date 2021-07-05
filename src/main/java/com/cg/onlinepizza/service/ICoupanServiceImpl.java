package com.cg.onlinepizza.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.dao.ICouponRepositoryDao;
import com.cg.onlinepizza.model.Coupan;
@Service
@Transactional
public class ICoupanServiceImpl implements ICoupanService{
	 @Autowired
     ICouponRepositoryDao dao;
	@Override
	public Coupan addCoupans(Coupan coupan) {
		Coupan c = dao.saveAndFlush(coupan);
		return c;
	}

	@Override
	public Coupan editCoupans(Coupan coupan) {
		if(!dao.existsById(coupan.getCoupanId())) {
			return null;
		}
		else {
			 
			 return dao.save(coupan);
		}
		
	}

	@Override
	public String deleteCoupans(int coupanId) throws CoupanIdNotFoundException  {
		if(!dao.existsById(coupanId)) {
			
			throw new CoupanIdNotFoundException("No Coupon found for the coupan id");
			
		}
		else
		{
			dao.deleteById(coupanId);
			
		return "Deleted Coupon";
		}
	}

	@Override
	public List<Coupan> viewCoupans() {
		return dao.findAll();
	}

	
}
