package com.taotao.cloud.sys.infrastructure.event.subscribe.guava;

import com.google.common.eventbus.Subscribe;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.eventbus.model.EventModel;
import org.greenrobot.eventbus.ThreadMode;

public class GuavaEventSubscriber {
	@Subscribe
	public void onMessage( EventModel<?> message) {
		LogUtils.info("收到消息：{}", message);
	}
}
