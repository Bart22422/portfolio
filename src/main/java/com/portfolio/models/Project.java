package com.portfolio.models;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private Date date;
	@Column(name = "tags")
	private String[] tags;

	@Column(name = "image")
	private File image;
	@Column(name = "url")
	private String url;

	public void setDate() {
		this.date = new Date();
	}

	public Project() {
	}

	public void addImage(File image) {
		this.image = image;
	}

	public void createTags(int length) {
		this.tags = new String[length];
	}

	public void setTag(int i, String nameTag) {
		this.tags[i] = nameTag;
	}

	public String getTag(int i) {
		if (tags.length >= i) {
			return tags[i];
		}
		return null;
	}

}