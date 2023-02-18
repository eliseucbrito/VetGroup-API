package com.api.vetgroup.services;

import com.api.vetgroup.models.*;
import com.api.vetgroup.repositories.RoleRepository;
import com.api.vetgroup.repositories.RoomAccessRepository;
import com.api.vetgroup.repositories.RoomRepository;
import com.api.vetgroup.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj =  repository.findById(id);
        return obj.get();
    }

    @Transactional
    public User insert(User newUser) {
        User userByUsername = repository.findByUsername(newUser.getUserName());
        if (userByUsername != null) {
            throw new IllegalArgumentException("Username is already registered!");
        }
        return repository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        repository.save(user);
    }

    public User findByFullName(String fullName) {
        User user = repository.findByFullName(fullName);

        if (user == null) throw new IllegalArgumentException("User of" +fullName+ " not found!");

        return user;
    }

    public ResponseEntity disableUser(String fullName) {
        User user = findByFullName(fullName);

        if (!user.getAccountNonLocked()) {
            throw new IllegalArgumentException("This account is already locked!");
        }

        if (!user.getEnabled()) {
            throw new IllegalArgumentException("This account is already disabled!");
        }

        user.setAccountNonLocked(false);
        user.setEnabled(false);
        update(user);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity enableUser(String fullName) {
        User user = findByFullName(fullName);

        if (user.getAccountNonLocked()) {
            throw new IllegalArgumentException("This account is already unlocked!");
        }

        if (user.getEnabled()) {
            throw new IllegalArgumentException("This account is already enabled!");
        }

        user.setAccountNonLocked(true);
        user.setEnabled(true);
        update(user);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity updateUserRole(RoleHistoric roleHistoric) {
        User user = repository.findByFullName(roleHistoric.getStaff().getFullName());

        //TODO change list of roles for unique role
        Optional<Role> role = roleRepository.findById(roleHistoric.getRole());
        List<Role> roleList = new ArrayList<>();
        roleList.add(role.get());

        user.setRoles(roleList);
        update(user);
        return ResponseEntity.noContent().build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " +username+ " not found!");
        }
    }
}
