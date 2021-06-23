package org.perscholas.security;

import org.perscholas.models.UserType;
import org.perscholas.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AppUserPrincipal implements UserDetails {

    //establish student object and auth group
    private Users users;
    private UserType userType;

    //constructor
    public AppUserPrincipal(Users users, UserType userType) {
        this.users = users;
        this.userType = userType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //if null return empty
        if(null == userType){
            return Collections.emptySet();
        }
        //establish new set of simple granted authorities
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        //add each auth group for the user
        grantedAuthorities.add(new SimpleGrantedAuthority(userType.getUserTypeName()));

        return grantedAuthorities;

    }

    @Override
    public String getPassword() {
        return this.users.getUPassword();
    }

    @Override
    public String getUsername() {
        return this.users.getUUsername();
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
