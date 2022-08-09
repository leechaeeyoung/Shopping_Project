package com.shopping.config;

import com.shopping.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PrincipalDetail implements UserDetails{
    private static final long serialVersion = 1L;
    private User user;
    public PrincipalDetail(User user){
        this.user=user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole();});
        return collector;
    }
    public String getPassword(){
        return user.getPassword();
    }
    public String getUsername(){
        return user.getUsername();
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
