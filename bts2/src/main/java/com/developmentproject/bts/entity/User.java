package com.developmentproject.bts.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "users")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4719144127712623243L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	

	@Column(name = "firstName", nullable = false)
	@NotEmpty(message = "First Name cannot be empty")
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	@NotEmpty(message = "Last Name cannot be empty")
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	@NotEmpty(message = "Email Name cannot be empty")
	@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;
	
	@Column(name = "password", nullable = false)
	@NotEmpty(message = "Password Name cannot be empty")
	private String password;
	
    private String username;
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Ticket> tickets;
    
    private int active = 1;
    @JsonManagedReference
	  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  
	  @JoinTable( name = "users_roles", joinColumns = @JoinColumn(name =
	  "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id") )
	  private Set<Role> roles = new HashSet<>();
	public User() {
		super();
	}
	
	
	
	public User(Long userId, String firstName, String lastName, String username,
			String phone, String password, String email,
			Set<Ticket> tickets, int active, Set<Role> roles) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.tickets = tickets;
		this.active = active;
		this.roles = roles;
		this.username = username;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}



//	public String getUsername() {
//		return username;
//	}



	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Set<Ticket> getTickets() {
		return tickets;
	}
	
	
	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	public int getActive() {
		return active;
	}
	
	
	public void setActive(int active) {
		this.active = active;
	}
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
	    return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
	            + ", password=" + password + ", username=" + username + ", tickets=" + tickets + ", active=" + active
	            + ", roles=" + roles + "]";
	}

	



	@Override
	public int hashCode() {
		return Objects.hash(active, email, firstName, lastName, password, roles, tickets, userId, username);
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
		return active == other.active && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(tickets, other.tickets) && Objects.equals(userId, other.userId)
				&& Objects.equals(username, other.username);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        } 
        return authorities;
	}
	@Override
    public String getPassword() {
        return password;
    }


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
    public boolean isEnabled() {
        return this.getActive() == 1;
    }

	@Override
	public String getUsername() {
		
		return this.getEmail();
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}




	

	
}
