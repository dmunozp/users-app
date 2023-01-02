/**
 * 
 */
package com.devs4j.users.models;

/**
 * @author dmunpalo
 *
 */
public class AuditDetails {

	private String createdBy;
	private String roleName;

	public AuditDetails() {
	}

	public AuditDetails(String createdBy, String roleName) {
		super();
		this.createdBy = createdBy;
		this.roleName = roleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
