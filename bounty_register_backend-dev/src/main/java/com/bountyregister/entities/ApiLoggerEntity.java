package com.bountyregister.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "api_logger")
@Data
public class ApiLoggerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url")
	private String url;

	@Column(name = "method")
	private String method;

	@Column(name = "body", length = 10000)
	private String body;

	@Column(name = "host")
	private String host;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
}
