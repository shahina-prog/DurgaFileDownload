package com.file.upload.fileupload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorials")
public class CSVData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number")
	private String number;
	
	
	public CSVData() {
		super();
	}
	
	public CSVData(String id, String name, String number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "CSVData [id=" + id + ", name=" + name + ", number=" + number + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	

}
