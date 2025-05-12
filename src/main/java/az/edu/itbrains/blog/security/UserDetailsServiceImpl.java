package az.edu.itbrains.blog.security;

import az.edu.itbrains.blog.models.User;
import az.edu.itbrains.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user != null){
            org.springframework.security.core.userdetails.User loginUser = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.isEnabled(),
                    user.isCredentialsNonExpired(),
                    user.isAccountNonExpired(),
                    user.isAccountNonLocked(),
                    user.getAuthorities()
            );
            return loginUser;
        }
        throw  new UsernameNotFoundException("User not found");
    }
}