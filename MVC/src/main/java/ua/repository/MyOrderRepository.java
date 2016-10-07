package ua.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Integer>,JpaSpecificationExecutor<MyOrder> {
	
	

}
