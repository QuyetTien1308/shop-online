package com.example.shoponline.entities;

import com.example.shoponline.utils.UserRoleId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", length = 36, nullable = false)
	public String userId;
	
	@Id
	@Column(name = "role_key", length = 20, nullable = false)
	public String roleKey;
	
	public UserRole() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
}
