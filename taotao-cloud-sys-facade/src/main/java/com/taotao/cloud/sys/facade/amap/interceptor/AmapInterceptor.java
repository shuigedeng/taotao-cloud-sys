package com.taotao.cloud.sys.facade.amap.interceptor;

import com.taotao.boot.ddd.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.ddd.gateway.model.GatewayContext;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;

public class AmapInterceptor<T> implements GatewayPostInterceptor<T> {
	@Override
	public void intercept(GatewayResponse<T> response, GatewayContext context) {

	}

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return context.getCatchedException() != null;
	}
}
