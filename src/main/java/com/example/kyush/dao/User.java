package com.example.kyush.dao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="status")
	private Boolean status;
	
	@Column(name="login_otp")
	private Integer loginOtp;
	
	@Column(name="otp_generated_at")
	private Date otpGeneratedAt;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="modified_by")
	private Integer modifiedBy;
	
	@Column(name="modified_at")
	private Date modifiedAt;
	
	@Transient
	private String roleName;
	
	@PrePersist
	  protected void onCreate() {
		
		createdAt = new Date();
		
	  }
	
	@PreUpdate
	  protected void onUpdate() {

		modifiedAt = new Date();
		
	  }

}
