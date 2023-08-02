package com.bountyregister.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "customer_machine_slot")
@Data
public class CustomerMachineSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "start_time")
	private LocalDateTime startTime;
	
	@Column(name = "end_time")
	private LocalDateTime endTime;
	
	@Column(name = "booking_date")
	private Date bookingDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "calories_burn")
	private Double caloriesBurn;
	
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
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="machine_id")
	private MachineMaster machineMaster;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private CustomerMaster customerMaster;

}
