package com.example.kyush.dao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;
	
	@Column(name = "role_status")
	private boolean roleStatus;
	
	
	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modified_at")
	private Date modifiedAt;

}
