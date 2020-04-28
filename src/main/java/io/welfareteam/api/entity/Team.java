package io.welfareteam.api.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long		id;

	private String		name;

	@ManyToMany
	private List<User>	admins;

	@ManyToMany
	private List<User>	members;

	@ManyToOne
	private Board		board;

	@OneToOne
	private MailSetting	mailSetting;

	public Team() {

		super();
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public List<User> getMembers() {

		return members;
	}

	public void setMembers(List<User> members) {

		this.members = members;
	}

	public List<User> getAdmins() {

		return admins;
	}

	public void setAdmins(List<User> admins) {

		this.admins = admins;
	}

	public Board getBoard() {

		return board;
	}

	public void setBoard(Board board) {

		this.board = board;
	}

	
	public MailSetting getMailSetting() {
	
		return mailSetting;
	}

	
	public void setMailSetting(MailSetting mailSetting) {
	
		this.mailSetting = mailSetting;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Team other = (Team) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", admins=" + admins + ", members=" + members + ", board=" + board + ", mailSetting=" + mailSetting + "]";
	}

}
