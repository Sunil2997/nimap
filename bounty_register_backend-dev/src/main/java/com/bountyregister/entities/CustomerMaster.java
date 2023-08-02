package com.bountyregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "customer_master")
@Where(clause = "is_active = true")
@Data
public class CustomerMaster {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

	@Column(name = "passcode")
	@JsonIgnore
	private String passcode;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "is_active")
	private Boolean isActive = true;

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
	
	@Column(name = "weight")
	private String weight;
	
	@OneToMany(mappedBy = "customerMaster", fetch = FetchType.LAZY)
	private List<CustomerGym> customerGym;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "customerMaster")
	private List<CustomerMachineSlot> customerMachineSlot;

}
