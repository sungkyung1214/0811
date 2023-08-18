package com.ict.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TxServiceImpl {

	@Autowired
	private TxDAO txDAO;
	
	public int getInsert(TxVO txvo)throws Exception {
		return txDAO.getInsert(txvo);
	}
	
}
