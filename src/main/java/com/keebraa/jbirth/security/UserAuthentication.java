package com.keebraa.jbirth.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.keebraa.jbirth.dao.objects.DBUser;

public class UserAuthentication extends AbstractAuthenticationToken {

    public UserAuthentication(DBUser user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        setDetails(user);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        final DBUser user = (DBUser) getDetails();
        if (user == null) {
            return null;
        }
        return new UserDetails() {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public String getUsername() {
                return user.getLogin();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                for (String group : user.getGroups()) {
                    authorities.add(new SimpleGrantedAuthority(group));
                }
                return authorities;
            }
        };
    }
}
