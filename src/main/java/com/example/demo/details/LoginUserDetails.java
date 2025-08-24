package com.example.demo.details;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.LoginUser;

public class LoginUserDetails implements UserDetails {
	private final LoginUser loginUser;
	
	public LoginUserDetails(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	public Collection<? extends GrantedAuthority> getAuthorities(){
		//GrantedAuthority:役割に応じたアクセス許可 AuthorityUtilsはそれを簡易的に定義するクラス
		return AuthorityUtils.createAuthorityList("ROLE_" + loginUser.getRole());
	}
	 @Override 
	 public String getPassword() { 
	     return loginUser.getPassword(); 
	 }
	 
	 public String getUserId() { 
		  return loginUser.getUserId(); 
	 }
	 @Override 
	 public String getUsername() { 
	     return loginUser.getUserName(); 
	 } 
	 
	 // アカウントの期限切れ（期限切れなし） 
	 @Override 
	 public boolean isAccountNonExpired() { 
	     return true; 
	 } 
	 
	 // アカウントのロック状態（ロックは使用しない） 
	 @Override 
	 public boolean isAccountNonLocked() { 
	     return true; 
	 } 
	//パスワードの期限切れ（期限切れなし） 
	@Override 
	public boolean isCredentialsNonExpired() { 
	   return true; 
	} 
	
	// 有効なユーザー 
	@Override 
	public boolean isEnabled() { 
	   return true; 
	} 
} 

