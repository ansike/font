package com.fd.font.cloud.developer.business;

import com.fd.font.cloud.developer.vo.AccessTokenRequest;
import com.fd.font.cloud.developer.vo.AccessTokenResponse;

public interface AccessTokenBusiness {

	public AccessTokenResponse createAccessToken(AccessTokenRequest request);
	
}
