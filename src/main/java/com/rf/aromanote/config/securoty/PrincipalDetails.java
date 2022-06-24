package com.rf.aromanote.config.securoty;

import com.rf.aromanote.domain.common.login.Login;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private Login login;

    public PrincipalDetails(Login login){
        this.login = login;
    }

    public Login getMemberForLogin(){
        return login;
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        return login.getUsername();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        login.getRoleList().forEach(r -> {
            authorities.add(()->{ return r;});
        });
        return authorities;
    }
}
