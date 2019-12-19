package com.loyaltyone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Submissions {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE )
@Column(name = "postid")
private long postid;
private String post;
private String name;
private String city;
private String lat;
private String lng;
@Transient
private String temperature;
private String response;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getResponse() {
	return response;
}
public void setResponse(String response) {
	this.response = response;
}
public String getPost() {
	return post;
}
public void setPost(String post) {
	this.post = post;
}

public long getPostid() {
	return postid;
}
public void setPostid(long postid) {
	this.postid = postid;
}

public String getLat() {
	return lat;
}
public void setLat(String lat) {
	this.lat = lat;
}
public String getLng() {
	return lng;
}
public void setLng(String lng) {
	this.lng = lng;
}

public String getTemperature() {
	return temperature;
}
public void setTemperature(String temperature) {
	this.temperature = temperature;
}
/**
 * 
 */
public Submissions() {
	
}

/**
 * @param post
 * @param name
 * @param city
 * @param string 
 */
public Submissions(long postid, String post, String name, String response) {
	super();
	this.postid = postid;
	this.post = post;
	this.name = name;
	this.response = response;
}
/**
 * @param post
 * @param name
 * @param city
 * @param lat
 * @param lng
 */
public Submissions(String post, String name, String city, String lat, String lng) {
	super();
	this.post = post;
	this.name = name;
	this.city = city;
	this.lat = lat;
	this.lng = lng;
}



}
