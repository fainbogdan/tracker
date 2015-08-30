package com.tracker.web.service.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.web.dao.interfaces.RoleRepo;
import com.tracker.web.models.Role;
import com.tracker.web.service.interfaces.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleRepo roleRepo;
	
	@Autowired
	public void setRoleRepo(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public Role save(Role role) {
		int id=roleRepo.save(role);
		return roleRepo.getRole(id);
	}

}
