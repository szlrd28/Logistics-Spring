package newLog.newLog.sercurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, UserDetails> users = new HashMap<>();

    public CustomUserDetailsService() {

        UserDetails user1 = User.builder()
                .username("addressManager")
                .password(passwordEncoder().encode("pass"))
                .authorities("AddressManager")
                .build();

        UserDetails user2 = User.builder()
                .username("transportManager")
                .password(passwordEncoder().encode("pass"))
                .authorities("TransportManager")
                .build();

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return users.get(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}