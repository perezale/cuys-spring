package ar.com.cuys.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByNameIgnoreCase(String name);

}
