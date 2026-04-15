/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.sys.application.service.commad.impl;

import com.taotao.boot.common.support.asserts.BusinessAssert;
import com.taotao.boot.data.datasource.wrapper.TransactionSynchronizationWrapper;
import com.taotao.boot.ddd.model.event.EventDispatcher;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.application.dto.own.user.command.AssignUserRolesCommand;
import com.taotao.cloud.sys.application.service.commad.UserCommandService;
import com.taotao.cloud.sys.domain.aggregate.RoleAgg;
import com.taotao.cloud.sys.domain.aggregate.UserAgg;
import com.taotao.cloud.sys.domain.repository.RoleDomainRepository;
import com.taotao.cloud.sys.domain.repository.UserDomainRepository;
import com.taotao.cloud.sys.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserServiceImpl
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 20:50:41
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

	private final UserDomainRepository userDomainRepository;
	private final RoleDomainRepository roleDomainRepository;
	private final UserDomainService userDomainService;
	private final TransactionSynchronizationWrapper txSynchronizationWrapper;
	private final EventDispatcher eventDispatcher;

	@Override
	@Transactional
	public void assignRoles( AssignUserRolesCommand assignUseRolesCommand ) {

		// 1. 加载数据（5个聚合）
		// 2. 应用层校验（3-5个）
		// 3. 调用领域服务（2-3次）
		// 4. 保存多个聚合
		// 5. 调用外部服务
		// 6. 发送消息/事件
		// 7. 记录日志

		UserAgg userAgg = userDomainRepository.findById(BizId.fromValue(assignUseRolesCommand.userId()), Boolean.TRUE);

		Set<BizId> requestedRoleIds = assignUseRolesCommand.roleIds().stream()
			.map(BizId::fromValue).collect(Collectors.toSet());

		List<RoleAgg> assignableRoles = roleDomainRepository.findAssignableRoles(requestedRoleIds);

		validateRolesExist(requestedRoleIds, assignableRoles);

		userDomainService.assignRoles(userAgg, assignableRoles);

		userDomainRepository.save(userAgg);

		txSynchronizationWrapper.afterCommit(() -> eventDispatcher.dispatchEvents(userAgg));

		log.info("角色分配成功，管理员ID: {}, 角色数量: {}", assignUseRolesCommand.userId(),
			assignUseRolesCommand.roleIds().size());
	}

	private void validateRolesExist( Set<BizId> requested, List<RoleAgg> found ) {
		Set<BizId> foundIds = found.stream().map(RoleAgg::id).collect(Collectors.toSet());

		Set<BizId> missing = new HashSet<>(requested);
		missing.removeAll(foundIds);

		BusinessAssert.isTrue(!missing.isEmpty(), "角色不存在或不可分配: {}", missing);
	}

	//	private static final QUser USER = QUser.user;
	//
	//	private static final String DEFAULT_PASSWORD = "123456";
	//	private static final String DEFAULT_USERNAME = "admin";
	//
	//	private final IUserRelationService userRelationService;
	//	private final UserManager userManager;
	//
	//	@Override
	//	@Transactional(rollbackFor = Exception.class)
	//	public User saveUser(User sysUser) {
	//		if (Objects.nonNull(sysUser.getId())) {
	//			throw new BusinessException("不允许存在id值");
	//		}
	//		Optional<User> byIdWithColumns =
	//			findByIdWithColumns(sysUser.getId(), User::getId, User::getUsername, User::getPhone);
	//
	//		String phone = sysUser.getPhone();
	//		Boolean isExists = existsByPhone(phone);
	//		if (isExists) {
	//			throw new BusinessException(ResultEnum.USER_PHONE_EXISTS_ERROR);
	//		}
	//		String nickname = sysUser.getNickname();
	//		if (StrUtil.isBlank(nickname)) {
	//			sysUser.setNickname(DEFAULT_USERNAME);
	//		}
	//		String username = sysUser.getUsername();
	//		if (StrUtil.isBlank(username)) {
	//			sysUser.setUsername(DEFAULT_USERNAME);
	//		}
	//
	//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	//		sysUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
	//		return ir().save(sysUser);
	//	}
	//
	//	@Override
	//	@Transactional(rollbackFor = Exception.class)
	//	public User updateUser(User user) {
	//		if (Objects.isNull(user.getId())) {
	//			throw new BusinessException("id不能为空");
	//		}
	//
	//		// 此处修改用户角色
	//		userRelationService.remove(Wrappers.<UserRelation>lambdaQuery().eq(UserRelation::getId,
	// user.getId()));
	//		List<UserRelation> userRoles = new ArrayList<Long>()
	//			.stream()
	//			.map(item -> {
	//				UserRelation sysUserRole = new UserRelation();
	//				sysUserRole.setObjectId(item);
	//				sysUserRole.setUserId(user.getId());
	//				return sysUserRole;
	//			})
	//			.toList();
	//
	//		userRelationService.saveBatch(userRoles);
	//		return user;
	//	}
	//
	//	@Override
	//	@Transactional(rollbackFor = Exception.class)
	//	public Boolean restPass(Long userId, RestPasswordUserDTO restPasswordDTO) {
	//		String restPasswordPhone = restPasswordDTO.getPhone();
	//		User sysUser = getById(userId);
	//
	//		// String phone = sysUser.getPhone();
	//		// if (!Objects.equals(restPasswordPhone, phone)) {
	//		//	throw new BusinessException(ResultEnum.USER_PHONE_INCONSISTENT_ERROR);
	//		// }
	//		// BCryptPasswordEncoder passwordEncoder = AuthUtil.getPasswordEncoder();
	//		// BCryptPasswordEncoder passwordEncoder = AuthUtil.getPasswordEncoder();
	//		//
	//		// String oldPassword = restPasswordDTO.getOldPassword();
	//		// String password = sysUser.getPassword();
	//		// if (!AuthUtil.validatePass(oldPassword, password)) {
	//		//	throw new BusinessException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
	//		// }
	//		//
	//		// String newPassword = restPasswordDTO.getNewPassword();
	//		// return sysUserRepository.updatePassword(id, passwordEncoder.encode(newPassword));
	//		return true;
	//	}
	//
	//	@Override
	//	public Boolean existsByPhone(String phone) {
	//		// BooleanExpression phonePredicate = USER.phone.eq(phone);
	//		// return cr().exists(phonePredicate);
	//		return true;
	//	}
	//
	//	@Override
	//	public Boolean existsById(Long id) {
	//		// BooleanExpression phonePredicate = USER.id.eq(id);
	//		// return cr().exists(phonePredicate);
	//		return true;
	//	}
	//
	//
	//	@Override
	//	@Transactional(rollbackFor = Exception.class)
	//	public Boolean updateUserRoles(Long userId, Set<Long> roleIds) {
	//		return userRelationService.saveUserRoles(userId, roleIds);
	//	}
	//
	//	// @Override
	//	// public boolean doPostSignUp(User user) {
	//	//    //进行账号校验
	//	//    User sysUser = findSecurityUserByUser(new User().setUsername(user.getUsername()));
	//	//    if (ObjectUtil.isNull(sysUser)) {
	//	//        throw new BaseException("账号不存在");
	//	//    }
	//	//    Integer userId = sysUser.getId();
	//	//    try {
	//	//        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername()去验证用户名和密码，
	//	//        // 如果正确，则存储该用户名密码到security 的 context中
	//	//        authenticationManager.authenticate(new
	//	// UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	//	//    } catch (Exception e) {
	//	//        if (e instanceof BadCredentialsException) {
	//	//            throw new BaseException("用户名或密码错误", 402);
	//	//        } else if (e instanceof DisabledException) {
	//	//            throw new BaseException("账户被禁用", 402);
	//	//        } else if (e instanceof AccountExpiredException) {
	//	//            throw new BaseException("账户过期无法验证", 402);
	//	//        } else {
	//	//            throw new BaseException("账户被锁定,无法登录", 402);
	//	//        }
	//	//    }
	//	//    //将业务系统的用户与社交用户绑定
	//	//    socialRedisHelper.doPostSignUp(user.getKey(), userId);
	//	//    return true;
	//	// }
	//
	//
	//
	//
	//
	//	@Override
	//	public User registe(User user) {
	//		this.baseMapper.insert(user);
	//
	//		String phone = this.decrypt(user.getPhone());
	//		String phoneKeywords = this.phoneKeywords(phone);
	//
	//		this.baseMapper.insertPhoneKeyworkds(user.getId(),phoneKeywords);
	//		return user;
	//	}
	//
	//	@Override
	//	public List<User> getPersonList(String phoneVal) {
	//		if (phoneVal != null) {
	//			return this.baseMapper.queryByPhoneEncrypt(this.encrypt(phoneVal));
	//		}
	////		return this.personDao.queryList(phoneVal);
	//		return new ArrayList<>();
	//	}
	//
	//
	//	private String phoneKeywords(String phone) {
	//		String keywords = this.keywords(phone, 4);
	//		LogUtils.info("",keywords.length());
	//		return keywords;
	//	}
	//	//分词组合加密
	//	private String keywords(String word, int len) {
	//		StringBuilder sb = new StringBuilder();
	//		for (int i = 0; i < word.length(); i++) {
	//            int end = i + len;
	//			String sub1 = word.substring(i, end);
	//			sb.append(this.encrypt(sub1));
	//			if (end == word.length()) {
	//				break;
	//			}
	//		}
	//		return sb.toString();
	//	}
	//	public String encrypt(String val) {
	//		//这里特别注意一下，对称加密是根据密钥进行加密和解密的，加密和解密的密钥是相同的，一旦泄漏，就无秘密可言，
	//		//“fanfu-csdn”就是我自定义的密钥，这里仅作演示使用，实际业务中，这个密钥要以安全的方式存储；
	//		byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(),
	// "fanfu-csdn".getBytes()).getEncoded();
	//		SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
	//		String encryptValue = aes.encryptBase64(val);
	//		return encryptValue;
	//	}
	//
	//	public String decrypt(String val) {
	//		//这里特别注意一下，对称加密是根据密钥进行加密和解密的，加密和解密的密钥是相同的，一旦泄漏，就无秘密可言，
	//		//“fanfu-csdn”就是我自定义的密钥，这里仅作演示使用，实际业务中，这个密钥要以安全的方式存储；
	//		byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(),
	// "fanfu-csdn".getBytes()).getEncoded();
	//		SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
	//		String encryptValue = aes.decryptStr(val);
	//		return encryptValue;
	//	}
}
