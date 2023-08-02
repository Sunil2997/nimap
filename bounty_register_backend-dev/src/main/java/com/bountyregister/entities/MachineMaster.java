package com.bountyregister.entities;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "machine_master")
@Data
public class MachineMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "machine_name")
	private String machineName;

	@Column(name = "video_file_path")
	private String videoFilePath;

	@Column(name = "slots")
	private Duration slots;
	
	@Column(name = "machine_met")
	private String machineMet;
	
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
	
	@OneToMany(mappedBy = "machineMaster",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CustomerMachineSlot> customerMachineSlot;
	
	@OneToOne(fetch  =  FetchType.EAGER)
	@JoinColumn(name = "device_id")
	private DeviceMaster deviceMaster;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private GymMaster gymMaster;
	
}
