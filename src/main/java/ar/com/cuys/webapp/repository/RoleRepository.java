package ar.com.cuys.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
