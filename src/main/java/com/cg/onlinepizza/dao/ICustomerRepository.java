package com.cg.onlinepizza.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.onlinepizza.model.Customer;
/***************************************************************************************************************************
 * Interface: ICustomerRepository
 * Description: It is used to perform all the curd operation in the dao
 * Created By-BANHISHIKA CHANDA
 * Created Date -  15-05-2021 
 * 
 ***************************************************************************************************************************/

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
	

}
