package com.taskknexel.taskisknexel.repositories;

import com.taskknexel.taskisknexel.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositories extends JpaRepository<Users,Long>{
}
