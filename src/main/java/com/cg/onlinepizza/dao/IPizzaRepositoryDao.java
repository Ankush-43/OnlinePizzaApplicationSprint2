package com.cg.onlinepizza.dao;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.cg.onlinepizza.Exceptions.InvalidMinCostException;
import com.cg.onlinepizza.model.Pizza;

/***************************************************************************************************************************
 * Interface: IPizzaRepositoryDao
 * Description: It is used to perform all the curd operation in the dao
 * Created By-BANHISHIKA CHANDA
 * Created Date -  15-05-2021 
 * 
 ***************************************************************************************************************************/

@Repository
public interface IPizzaRepositoryDao extends JpaRepository<Pizza, Integer>{
	@Query("select p from Pizza  p where p.pizzaCost>?1 and p.pizzaCost<?2")
	Optional<List<Pizza>> viewPizzaList(double minCost, double maxCost);
	
	@Query("select max(pizzaCost) from Pizza") 
	double MaxPizza();

	
	String deleteById(int pizzaId);
}
