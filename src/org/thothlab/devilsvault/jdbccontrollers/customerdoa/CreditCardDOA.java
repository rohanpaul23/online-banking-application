package org.thothlab.devilsvault.jdbccontrollers.customerdoa;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thothlab.devilsvault.CustomerModel.CreditAccount;
import org.thothlab.devilsvault.CustomerModel.Customer;
import org.thothlab.devilsvault.CustomerModel.TransactionModel;
import org.thothlab.devilsvault.jdbccontrollers.RequestDOA.RequestMapper;
import org.thothlab.devilsvault.jdbccontrollers.model.Request;

@Repository ("creditCardDOA")
public class CreditCardDOA  {
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Get the credit account details for the user.
	 * @param customer
	 * @return
	 */
	public CreditAccount getCreditAccount(Customer customer) {
		
		String query = "select * from credit_card_account_details WHERE id = "+
				customer.getID();
				
		return null;
	}
	
	/**
	 * Returns all the credit card transaction for the user.
	 * @return
	 */
	public List<TransactionModel> getAllTransactions(CreditAccount account) {
		
		String query = "select * from completed_transactions WHERE payee_account_number = "+
		account.getAccountNumber()
		+" OR payer_account_number = "+account.getAccountNumber();
		List<TransactionModel> transactionList = jdbcTemplate.query(query, 
                new TransactionMapper());
		return transactionList;
	}
	
	
	

}
