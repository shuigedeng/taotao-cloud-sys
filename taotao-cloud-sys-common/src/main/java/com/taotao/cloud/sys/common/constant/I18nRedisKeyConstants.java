package com.taotao.cloud.sys.common.constant;

/**
 * 国际化使用到的 redis 缓存key
 *
 * @author hccake
 */
public final class I18nRedisKeyConstants {

	private I18nRedisKeyConstants() {

	}
	/** 部门范围限制的字段名称 */
	private String scopeDeptFiledName = "dept_id";
	/** 公司范围限制的字段名称 */
	private String scopeOrgFiledName = "org_id";
	/** 个人范围限制的字段名称 */
	private String scopeSelfFiledName = "create_by";
	/**
	 * i18nData 的数据存储缓存 key
	 * <ul>
	 * <li>type: String</li>
	 * <li>fullKey: prefix:code:languageTag</li>
	 * <ul/>
	 */
	public static final String I18N_DATA_PREFIX = "i18n-data";

	/**
	 * 删除 i18n data 消息的 channel 名
	 */
	public static final String CHANNEL_I18N_DATA_UPDATED = "channel:i18n-data-updated";

}
