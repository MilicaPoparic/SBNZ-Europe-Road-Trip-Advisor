package com.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.model.User;
import com.sbnz.repository.UserRepository;

// Ovaj servis je namerno izdvojen kao poseban u ovom primeru.
// U opstem slucaju UserServiceImpl klasa bi mogla da implementira UserDetailService interfejs.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Funkcija koja na osnovu username-a iz baze vraca objekat User-a
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // ako se ne radi nasledjivanje, paziti gde sve treba da se proveri email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return user;
        }
    }

    // Funkcija pomocu koje korisnik menja svoju lozinku
    public void changePassword(String oldPassword, String newPassword) throws Exception {

        // Ocitavamo trenutno ulogovanog korisnika
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = ((User) currentUser.getPrincipal()).getEmail();
        String checkPassword = ((User) currentUser.getPrincipal()).getPassword();
        
        if (passwordEncoder.matches(checkPassword, oldPassword)) {
        	throw new Exception("Old password incorrect.");
        }
        if (authenticationManager != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            return;
        }
        User user = (User) loadUserByUsername(username);

        // pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
        // ne zelimo da u bazi cuvamo lozinke u plain text formatu
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
