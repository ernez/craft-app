package com.ernez.craftapp.domain;

import com.ernez.craftapp.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class AppUser extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private Boolean locked = false;
	private Boolean enabled = false;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public static UserDto mapToDto(AppUser appUser) {
			return UserDto.builder()
					.id(appUser.getId())
					.firstName(appUser.getFirstName())
					.lastName(appUser.getLastName())
					.email(appUser.getEmail())
					.phoneNumber(appUser.getPhoneNumber())
					.build();
	}


}
