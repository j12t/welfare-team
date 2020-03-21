package io.welfareteam.api.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NaturalId
	private String login;
	
	private String password;
	
	private String enabled;
	
	private String email;
	
	private String name;
	
	private String firstname;
	
	@ElementCollection
	@CollectionTable(name = "user_role")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> roles;
	
	@ManyToMany(mappedBy="admins", targetEntity = Team.class)
	private List<Team> managedTeam;
	
	@ManyToMany(mappedBy="members", targetEntity = Team.class)
	private List<Team> teams;

	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", firstname=" + firstname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Team> getManagedTeam() {
		return managedTeam;
	}

	public void setManagedTeam(List<Team> managedTeam) {
		this.managedTeam = managedTeam;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String aLogin) {
		login = aLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String aPassword) {
		password = aPassword;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> aRoles) {
		roles = aRoles;
	}

	/**
	 * @return the field enabled
	 */
	public String getEnabled() {
		return enabled;
	}

	/**
	 * @param aEnabled the field enabled to set
	 */
	public void setEnabled(String aEnabled) {
		enabled = aEnabled;
	}
	
}
