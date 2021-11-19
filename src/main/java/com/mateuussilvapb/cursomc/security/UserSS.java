package com.mateuussilvapb.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mateuussilvapb.cursomc.domain.enums.Perfil;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	/*
	 * Define o perfil do usuário e, por consequência, as autorições que esse
	 * usuário tem ou não.
	 */
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}

	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	/*
	 * Esse método verifica se a conta do usuário expirou ou não. Por padrão, ele
	 * está retornando true, ou seja, a conta nunca expira. Porém, caso seja
	 * decidido, é possível implementar toda uma lógica de negócio para, caso a
	 * conta não cumpra com determinadas exigências, ela expire.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * Esse método verifica se a conta está ou não bloqueada. Por padrão, a conta
	 * não ficará bloqueada. Porém, semelhante ao método que verifica se a conta
	 * está ou não expirada, é possível implementar toda uma lógica de negócio para
	 * fazer essa checagem.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
}