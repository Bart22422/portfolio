package com.portfolio.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data

public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "path")
	private String path;
	private byte[] file;
	@Column(name = "isPreview")
	private boolean isPreview;
	@Column(name = "contentType")
	private String contentType;
	@Column(name = "size")
	private long size;
	/*
	 * @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER) private
	 * Project project;
	 */

}
