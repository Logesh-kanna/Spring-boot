package com.project.e_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.e_commerce.Model.RoleModel;
import com.project.e_commerce.Model.RoleModel.Role;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

	@Query("SELECT u FROM RoleModel u WHERE u.name = :name")
	public RoleModel findByName(@Param("name") Role name);
	
}
