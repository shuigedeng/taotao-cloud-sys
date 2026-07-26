package com.taotao.cloud.sys.domain.aggregate;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.taotao.boot.common.support.asserts.BusinessAssert;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.common.enums.EnabledEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.core.collection.CollUtil.addIfAbsent;
import static com.taotao.cloud.sys.common.constant.SysConstants.SERVER_NAME;

/**
 * 角色聚合根 封装角色相关的业务规则和一致性边界
 *
 * @author ouyucheng
 * @date 2025/12/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleAgg extends AggregateRoot<BizId> {

	/**
	 * 角色编码（唯一）
	 */
	private String code;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色描述
	 */
	private String description;

	private EnabledEnum enabled = EnabledEnum.ENABLED;


	/**
	 * 权限ID列表
	 */
	private List<String> permissionIds = new ArrayList<>();
	private String remark;

	/**
	 * 创建角色
	 *
	 * @param code 角色编码
	 * @param name 角色名称
	 * @param description 角色描述
	 * @return 角色对象
	 */
	public static RoleAgg create( String code, String name, String description ) {
		RoleAgg roleAgg = new RoleAgg();
		roleAgg.setCode(code);
		roleAgg.setName(name);
		roleAgg.setDescription(description);
		roleAgg.setPermissionIds(new ArrayList<>());
		roleAgg.setServiceId(SERVER_NAME);
		roleAgg.setSourceName(SERVER_NAME);
		return roleAgg;
	}

	/**
	 * 更新角色信息
	 *
	 * @param name 角色名称
	 * @param description 角色描述
	 */
	public void updateNameOrDesc( String name, String description ) {
		if (StrUtil.isNotBlank(name)) {
			this.name = name;
		}
		if (StrUtil.isNotBlank(description)) {
			this.description = description;
		}
	}

	/**
	 * 添加权限
	 *
	 * @param permissionId 权限ID
	 */
	public void addPermission( String permissionId ) {

		BusinessAssert.isTrue(StrUtil.isNotBlank(permissionId), "权限标识不能为空");

		addIfAbsent(permissionIds, permissionId);
	}

	/**
	 * 批量添加权限
	 *
	 * @param permissionIds 权限ID列表
	 */
	public void addPermissions( List<String> permissionIds ) {
		if (CollectionUtil.isEmpty(permissionIds)) {
			LogUtils.warn("待添加的权限列表为空或null，跳过本次批量添加");
			return;
		}

		List<String> validIds = permissionIds.stream()
			.filter(StringUtils::isNotBlank)
			.distinct()
			.toList();

		for (String permissionId : validIds) {
			addPermission(permissionId);
		}
	}

	/**
	 * 移除权限
	 *
	 * @param permissionId 权限ID
	 */
	public void removePermission( String permissionId ) {
		if (permissionId != null) {
			permissionIds.remove(permissionId);
		}
	}

	/**
	 * 批量移除权限
	 *
	 * @param permissionIds 权限ID列表
	 */
	public void removePermissions( List<String> permissionIds ) {
		if (permissionIds != null) {
			this.permissionIds.removeAll(permissionIds);
		}
	}

	/**
	 * 清空权限
	 */
	public void clearPermissions() {
		permissionIds.clear();
	}

	/**
	 * 判断是否拥有某个权限
	 *
	 * @param permissionId 权限ID
	 * @return 是否拥有
	 */
	public boolean hasPermission( String permissionId ) {
		return permissionIds.contains(permissionId);
	}











	/**
	 * 判断是否启用
	 *
	 * @return 是否成功
	 * @since 2022.03
	 */

	public boolean isEnabled() {
		return EnabledEnum.ENABLED.equals(this.enabled);
	}
}
