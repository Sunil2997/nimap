package com.bountyregister.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_logger")
@Data
public class LoggerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity userId;

	@CreationTimestamp
	@Column(name = "login_at")
	private Date loginAt;

	@Column(name = "expired_at")
	private Date expiredAt;

	@Column(name = "bounty_token")
	private String bountyToken;

}
