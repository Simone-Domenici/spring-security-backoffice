package org.backoffice.java.videogames_spring_backoffice.security;

import java.util.HashSet;
import java.util.Set;

import org.backoffice.java.videogames_spring_backoffice.model.Role;
import org.backoffice.java.videogames_spring_backoffice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DataBaseUserDetails implements UserDetails{

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DataBaseUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.authorities = new HashSet<GrantedAuthority>();

        for (Role userRole : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    @Override
    public boolean isEnabled(){
        return true;
    }
    
}
