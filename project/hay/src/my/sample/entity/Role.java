package my.sample.entity;

/**
 * 
 * 
 * 
 */
public enum Role {
	GUEST("guest"), DAMNED("damned"), USER("user"), ADMIN("admin");
	private String roleName = null;
	private static Role[] realValues = null;

	private Role(String s) {
		roleName = s;
	}

	public static Role lookUp(String name) {
		if (name == null) {
			return null;
		}
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getName().equals(name)) {
				return values()[i];
			}
		}
		return null;
	}

	public String getName() {
		return roleName;
	}

	public static Role[] realValues() {
		if (realValues == null) {
			realValues = new Role[values().length - 1];
			for (int i = 0, j = 0; i < values().length; i++) {
				if (values()[i] != GUEST) {
					realValues[j++] = values()[i];
				}
			}
		}
		return realValues;
	}
}
