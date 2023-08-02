package com.bountyregister.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Table(name = "permissions")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE permissions SET is_active=false WHERE id=?")
@Data
public class PermissionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "action_name")
	private String actionName;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active")
	private boolean isActive = true;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@JoinColumn(name = "created_by")
	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "method")
	private String method;

	@Column(name = "url")
	private String url;

}
