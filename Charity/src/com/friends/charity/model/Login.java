package com.friends.charity.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.HashPartitioning;
import org.eclipse.persistence.annotations.Partitioned;
import org.eclipse.persistence.annotations.PinnedPartitioning;
import org.hibernate.annotations.Columns;

@Entity
@Table(name = "LOGIN")


@NamedQueries({
		@NamedQuery(name = "selectUsernamePassword", query = "select u from Login  u where u.usernamePassword.username=:username"),
		@NamedQuery(name = "all", query = "select u from Login u"),
		@NamedQuery(name = "loginTest", query = "select u from Login u where u.usernamePassword.username=:username and u.usernamePassword.password =:password"),
		@NamedQuery(name = "byId", query = "select u from Login u where u.id=:id"),

})
public class Login extends BaseEntity {
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UsernamePassword usernamePassword;

	public User getUser() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UsernamePassword getUsernamePassword() {
		if (usernamePassword == null) {
			usernamePassword = new UsernamePassword();
		}
		return usernamePassword;
	}

	public void setUsernamePassword(UsernamePassword usernamePassword) {
		this.usernamePassword = usernamePassword;
	}

}