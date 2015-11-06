package com.lokesh.tracker.web.service.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.tracker.web.dao.interfaces.RoleRepo;
import com.lokesh.tracker.web.models.Role;
import com.lokesh.tracker.web.service.interfaces.RoleService;

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
