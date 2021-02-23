package idv.cm.db;

public class UserVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String email;
//	private BookList<String> books;
	
	public UserVO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(obj instanceof UserVO) {
			UserVO user = (UserVO) obj;
			return name.equals(user.getName())&& password.equals(user.getPassword());
		}
		return false;
	}

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", password=" + password + ", email=" + email + "]";
	}

	
}
