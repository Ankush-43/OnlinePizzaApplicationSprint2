package com.cg.onlinepizza.service;

import java.util.List;

import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.model.Coupan;

public interface ICoupanService {
	Coupan addCoupans(Coupan coupan);

	Coupan editCoupans(Coupan coupan);//throws InvalidCoupanOperationException;

	String deleteCoupans(int coupanId) throws  CoupanIdNotFoundException;

	List<Coupan> viewCoupans();

}
