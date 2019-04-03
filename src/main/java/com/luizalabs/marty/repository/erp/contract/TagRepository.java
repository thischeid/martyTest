package com.luizalabs.marty.repository.erp.contract;

import java.util.List;

import com.luizalabs.marty.model.Orders;


public interface TagRepository {

	List<Orders> findAllByBranchOriginAndOrderNumbers(int branchOrigin, List<Long> orderNumbers);
	
}
