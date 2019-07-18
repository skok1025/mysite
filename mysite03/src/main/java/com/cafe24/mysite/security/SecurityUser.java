package com.cafe24.mysite.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{
   private static final long serialVersionUID = 1L;
   
   //security fields
   private Collection<? extends GrantedAuthority> authorities;
   private String username;// email (Principal) biz name
   private String password;// credential
   
   //domain fields(principal, 보호할 사용자 중요 데이터)
   private String name; // 성명 domain data
   private Long no;
   
   
   public Long getNo() {
      return no;
   }

   public void setNo(Long no) {
      this.no = no;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
      this.authorities = authorities;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

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

}