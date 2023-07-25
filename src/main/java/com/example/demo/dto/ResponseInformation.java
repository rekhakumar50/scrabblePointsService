package com.example.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseInformation implements Serializable {
	private static final long serialVersionUID = 2923790425028989848L;
	
	private int code;
	private String message;
	
}
