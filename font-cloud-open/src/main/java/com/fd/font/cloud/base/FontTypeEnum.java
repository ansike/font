package com.fd.font.cloud.base;

public enum FontTypeEnum {
	NOT_DELETED((byte)0),
	DELETED((byte)1), 
	FONT_NOT_AUTH((byte)0),
	FONT_AUTH((byte)1)
	;
	private Byte type;

	private FontTypeEnum(Byte type) {
		this.type = type;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}
	
}
