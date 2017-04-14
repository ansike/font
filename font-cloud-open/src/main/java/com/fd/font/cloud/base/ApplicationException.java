package com.fd.font.cloud.base;

public class ApplicationException extends RuntimeException {

	
private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String msg;
	private Object data;
	
	public ApplicationException(ReturnCode type) {
		setCode(type.getKey());
		setMsg(type.getValue());
	}
	
	public ApplicationException(ReturnCode type,Object data) {
		setCode(type.getKey());
		setMsg(type.getValue());
		setData(data);
	}
	
	public ApplicationException() {
		super();
	}
	public ApplicationException(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public ControllerResult getResult(){
		ControllerResult result=new ControllerResult(getCode(),getMsg(),getData());
		return result;
	}
}
