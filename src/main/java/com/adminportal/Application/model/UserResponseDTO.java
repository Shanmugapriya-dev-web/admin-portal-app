package com.adminportal.Application.model;

public class UserResponseDTO {
	private int userId;
	private String userName;
	private int roleId;
	private String roleName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserResponseDTO [userId=" + userId + ", userName=" + userName + ", roleId=" + roleId + ", roleName="
				+ roleName + "]";
	}
}
