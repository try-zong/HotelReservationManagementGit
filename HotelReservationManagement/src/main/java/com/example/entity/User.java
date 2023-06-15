package com.example.entity;
import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String account;  
	private int  age;
	private String name;  
	private String password;  
}
