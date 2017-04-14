package com.fd.font.cloud.common.vo;

import java.util.ArrayList;
import java.util.List;

public class Errors {

	private List<ErrorCode> errors = new ArrayList();

	public List<ErrorCode> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorCode> errors) {
		this.errors = errors;
	}
	
	public void add(ErrorCode code){
		errors.add(code);
	}
	
}
