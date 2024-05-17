package com.example.HomeTask19.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

	@CreatedDate
	@Column(name = "last_updated_date", nullable = false)
	private LocalDate lastUpdateDate;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDate createDate;
}
