package uz.pdp.transfermoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Users;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    List<Users> userList = new ArrayList<>();

    @PostConstruct
    public void init(){
        userList = Arrays.asList(
                new Users(1L,"test1", passwordEncoder.encode("test1"), new ArrayList<>()),
                new Users(2L,"test2", passwordEncoder.encode("test2"), new ArrayList<>()),
                new Users(3L, "test3", passwordEncoder.encode("test3"), new ArrayList<>()),
                new Users(4L,"test4", passwordEncoder.encode("test4"), new ArrayList<>())
        );
    }

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {

        for (Users users : userList) {
            if (users.getUsername().equals(username))
                return users;
        }
        throw new UsernameNotFoundException("user not found");
    }

    public Users findUserById(Long id){
        return userList.stream().filter( users -> id.equals(users.getId())).findAny().orElse(null);
    }

}
