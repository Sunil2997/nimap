package com.bountyregister.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "error_logger")
@Data
public class ErrorLoggerEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message", length = 100000)
	private String message;

	@Column(name = "url")
	private String url;

	@Column(name = "method")
	@Enumerated(EnumType.STRING)
	private MethodEnum method;

	@Column(name = "host")
	private String host;

	@Column(name = "body", length = 10000)
	private String body;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

}
