package com.example.backtoken.service;

import com.example.backtoken.model.Usertk;
import com.example.backtoken.repository.UsertkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomDetailService implements UserDetailsService {
    private UsertkRepository usertkDAO;

    public CustomDetailService(UsertkRepository usertkDAO) {
        this.usertkDAO = usertkDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usertk usertk = Optional.ofNullable(usertkDAO.findByUs_login(login))
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        List<GrantedAuthority> authorityUser = AuthorityUtils.createAuthorityList("ROLE_GUEST","ROLE_USER");
        List<GrantedAuthority> authorityGuest = AuthorityUtils.createAuthorityList("ROLE_GUEST");
        return new org.springframework.security.core.userdetails
                .User(usertk.getUs_login(), usertk.getUs_senha(), usertk.getUs_id() > 0 ?authorityUser:authorityUser);
    }
}
