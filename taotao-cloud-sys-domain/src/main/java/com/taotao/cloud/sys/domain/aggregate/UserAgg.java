package com.taotao.cloud.sys.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.common.enums.UserStatusEnum;
import com.taotao.cloud.sys.domain.event.AuthChangeEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.taotao.cloud.sys.common.constant.SysConstants.SERVER_NAME;

/**
 * 管理员聚合根 封装管理员相关的业务规则和一致性边界
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAgg extends AggregateRoot<BizId> {

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 管理员状态
	 */
	private UserStatusEnum status;

	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 角色ID列表
	 */
	private List<BizId> roleIds = new ArrayList<>();

	/**
	 * 创建管理员
	 *
	 * @param mobile 手机号
	 * @return 管理员对象
	 */
	public static UserAgg create( String mobile ) {
		UserAgg userAgg = new UserAgg();
		userAgg.setMobile(mobile);
		userAgg.setStatus(UserStatusEnum.NORMAL);
		userAgg.setRoleIds(new ArrayList<>());
		userAgg.setServiceId(SERVER_NAME);
		userAgg.setSourceName(SERVER_NAME);
		return userAgg;
	}

	/**
	 * 更新管理员信息
	 *
	 * @param realName 真实姓名
	 * @param mobile 手机号
	 */
	public void updateInfo( String realName, String mobile ) {
		if (StrUtil.isNotBlank(realName)) {
			this.realName = realName;
		}
		if (StrUtil.isNotBlank(mobile)) {
			this.mobile = mobile;
		}
	}


	/**
	 * 启用管理员
	 */
	public void enable() {
		this.status = UserStatusEnum.NORMAL;
	}

	/**
	 * 冻结管理员
	 */
	public void freeze() {
		this.status = UserStatusEnum.FROZEN;
	}


	/**
	 * 更新最后登录时间
	 */
	public void updateLastLoginTime() {
		this.lastLoginTime = new Date();
	}

	/**
	 * 添加角色
	 *
	 * @param roleId 角色ID
	 */
	public void addRole( Long roleId ) {
		if (roleId != null) {
			BizId roleBizId = BizId.fromValue(roleId);
			if (!roleIds.contains(roleBizId)) {
				roleIds.add(roleBizId);
			}
		}
	}

	/**
	 * 批量添加角色
	 *
	 * @param roleIds 角色ID列表
	 */
	public void addRoles( List<Long> roleIds ) {
		if (roleIds != null) {
			for (Long roleId : roleIds) {
				addRole(roleId);
			}
		}
	}


	/**
	 * 批量移除角色
	 *
	 * @param roleIds 角色ID列表
	 */
	public void removeRoles( List<BizId> roleIds ) {
		if (roleIds != null) {
			this.roleIds.removeAll(roleIds);
		}
	}

	/**
	 * 清空角色
	 */
	public void clearRoles() {
		roleIds.clear();
	}

	/**
	 * 判断管理员是否正常
	 *
	 * @return 是否正常
	 */
	public boolean isNormal() {
		return status != null && status.isNormal();
	}

	/**
	 * 判断管理员是否删除
	 *
	 * @return 是否删除
	 */
	public boolean isDeleted() {
		return status != null && status.isDeleted();
	}

	/**
	 * 判断管理员是否可用
	 *
	 * @return 是否可用
	 */
	public boolean isAvailable() {
		return isNormal();
	}


	/**
	 * 判断是否拥有某个角色
	 *
	 * @param roleId 角色ID
	 * @return 是否拥有
	 */
	public boolean hasRole( String roleId ) {
		return roleIds.contains(roleId);
	}

	public void assignRoles( List<BizId> roleIds ) {
		this.roleIds = roleIds;

		AuthChangeEvent authChangeEvent = new AuthChangeEvent(this.roleIds);
		authChangeEvent.setAggregateId(this.id);
		publishEvent(authChangeEvent);
	}
}
