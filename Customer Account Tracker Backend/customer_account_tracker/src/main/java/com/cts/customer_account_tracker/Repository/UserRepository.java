package com.cts.customer_account_tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.customer_account_tracker.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

}
