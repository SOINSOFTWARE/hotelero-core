/**
 * 
 */
package com.soinsoftware.hotelero.core.controller;

import java.util.List;

import com.soinsoftware.hotelero.persistence.entity.Access;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 *
 */
public class AccessController {

	private static final String VIEW_ACCESS = "VRXA";

	private static final String CREATE_ACCESS = "CRXA";

	private static final String DELETE_ACCESS = "DRXA";

	private static final String VIEW_EMPLOYEE = "VEMP";

	private static final String CREATE_EMPLOYEE = "CEMP";

	private static final String UPDATE_EMPLOYEE = "UEMP";

	private static final String DELETE_EMPLOYEE = "DEMP";

	private static final String VIEW_ROLE = "VROLE";

	private static final String CREATE_ROLE = "CROLE";

	private static final String UPDATE_ROLE = "UROLE";

	private static final String DELETE_ROLE = "DROLE";

	private final List<Access> accesses;

	public AccessController(final List<Access> accesses) {
		super();
		this.accesses = accesses;
	}

	public boolean canAccessRoleMenu() {
		return canViewRoleInformation() || canCreateNewRoles() || canUpdateRoles() || canDeleteRoles();
	}

	public boolean canViewAccessInformation() {
		return hasAccess(VIEW_ACCESS);
	}

	public boolean canCreateNewAccesses() {
		return hasAccess(CREATE_ACCESS);
	}

	public boolean canDeleteAccess() {
		return hasAccess(DELETE_ACCESS);
	}

	public boolean canViewEmployeeInformation() {
		return hasAccess(VIEW_EMPLOYEE);
	}

	public boolean canCreateNewEmployees() {
		return hasAccess(CREATE_EMPLOYEE);
	}

	public boolean canUpdateEmployees() {
		return hasAccess(UPDATE_EMPLOYEE);
	}

	public boolean canDeleteEmployee() {
		return hasAccess(DELETE_EMPLOYEE);
	}

	public boolean canViewRoleInformation() {
		return hasAccess(VIEW_ROLE);
	}

	public boolean canCreateNewRoles() {
		return hasAccess(CREATE_ROLE);
	}

	public boolean canUpdateRoles() {
		return hasAccess(UPDATE_ROLE);
	}

	public boolean canDeleteRoles() {
		return hasAccess(DELETE_ROLE);
	}

	private boolean hasAccess(final String searchedCode) {
		boolean hasAccess = false;
		if (accesses != null && !accesses.isEmpty()) {
			for (final Access access : accesses) {
				if (access != null && access.getCode() != null && access.getCode().equals(searchedCode)) {
					hasAccess = true;
					break;
				}
			}
		}
		return hasAccess;
	}
}