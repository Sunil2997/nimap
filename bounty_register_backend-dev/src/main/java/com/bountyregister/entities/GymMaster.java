package com.bountyregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "gym_master")
@Data
public class GymMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "gym_name")
	private String gymName;

	@Column(name = "gym_address")
	private String gymAddress;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updatedBy;
	
	@OneToMany(mappedBy = "gymMaster", fetch = FetchType.LAZY)
	private List<CustomerGym> customerGym;
	
	@OneToMany(mappedBy = "gymMaster", fetch = FetchType.LAZY)
	private List<MachineMaster> machineMaster;
}
