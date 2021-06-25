package org.perscholas.security;

import org.perscholas.dao.IUserRepo;
import org.perscholas.dao.IUserTypeRepo;
import org.perscholas.models.UserType;
import org.perscholas.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    //establish repos
    private final IUserRepo iUserRepo;
    private final IUserTypeRepo iUserTypeRepo;

    //constructor
    @Autowired
    public AppUserDetailService(IUserRepo iUserRepo, IUserTypeRepo iUserTypeRepo) {
        this.iUserRepo = iUserRepo;
        this.iUserTypeRepo = iUserTypeRepo;
    }

    //load by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = iUserRepo.findByuUsername(username);
        if(users==null)
            throw new UsernameNotFoundException("Cannot find Username: " + username);
        UserType userType = this.iUserTypeRepo.findByuserTypeId(users.getUUserType().getUserTypeId());
        return new AppUserPrincipal(users,userType);
    }
}

