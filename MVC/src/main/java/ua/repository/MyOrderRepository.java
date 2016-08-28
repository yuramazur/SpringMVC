package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {

}
