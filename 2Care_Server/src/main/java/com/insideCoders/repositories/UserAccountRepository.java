package com.insideCoders.repositories;

import com.insideCoders.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer>
{
}
