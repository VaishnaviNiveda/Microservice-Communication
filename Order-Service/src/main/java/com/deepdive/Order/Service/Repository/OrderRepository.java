package com.deepdive.Order.Service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepdive.Order.Service.Model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
