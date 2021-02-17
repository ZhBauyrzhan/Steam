package model;

import org.mindrot.jbcrypt.BCrypt;

import com.j256.ormlite.field.DatabaseField;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User implements Model {
	@DatabaseField(id = true, columnName = "id")
	private int id; 
	@DatabaseField(columnName = "firstName")
	private String firstName;
	@DatabaseField(columnName = "lastName")
	private String lastName;
	@DatabaseField(columnName = "login")
	private String login;
	@DatabaseField(columnName = "phone")
	private String phone;
	@DatabaseField(columnName = "email")
	private String email;
	@DatabaseField(columnName = "password")
	private String password;
	@DatabaseField(columnName = "birthDay") 
	private String birthName;
	@DatabaseField(columnName = "user_role") 
	private String role;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAME = "lastName";
	public static final String FIELD_LOGIN = "login";
	public static final String FIELD_PASSWORD = "password";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PHONE = "phone";
	public static final String FIELD_BIRTH_DAY = "birthDay";
	public static final String FIELD_ROLE = "role";
	public static final String FIELD_ADMIN = "admin";
	public static final String FIELD_COMMON = "common";
	
	public User(int id, String firstName, String lastName, String login, String phone, String email, String password,
			String birthName, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.phone = phone;
		this.email = email;
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = hash;
		this.birthName = birthName;
		this.role = role;
	}
	
	public User() {
		super();
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthName() {
		return birthName;
	}
	public void setBirthName(String birthName) {
		this.birthName = birthName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthName == null) ? 0 : birthName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthName == null) {
			if (other.birthName != null)
				return false;
		} else if (!birthName.equals(other.birthName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
}