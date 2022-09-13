package com.sajath.order.Repository;

import com.sajath.order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Integer>{
}
