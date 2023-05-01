package fun.icpc.iris.irisonlinejudge.security;

import fun.icpc.iris.irisonlinejudge.permission.Permission;
import fun.icpc.iris.irisonlinejudge.user.User;
import fun.icpc.iris.irisonlinejudge.user.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Query the database to determine if the username exists.
        Optional<User> userOptional = userRepository.findByHandle(username);

        // If the user does not exist, throw UsernameNotFoundException.
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist");
        }

        User user = userOptional.get();

        // Query the user's permissions.
        Set<Permission> permissions = user.getPermissions();

        // Convert the permissions to a set of GrantedAuthority.
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Permission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }

        // If the user exists, return the user information.
        String password = user.getPassword();
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
