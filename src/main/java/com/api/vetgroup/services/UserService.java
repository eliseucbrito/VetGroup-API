package com.api.vetgroup.services;

import com.api.vetgroup.models.Room;
import com.api.vetgroup.models.RoomAccessList;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.User;
import com.api.vetgroup.repositories.RoomAccessRepository;
import com.api.vetgroup.repositories.RoomRepository;
import com.api.vetgroup.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

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
    public User insert(User newRoom) {
        return repository.save(newRoom);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(User room) {
        repository.save(room);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("FIND ONE USER BY NAME "+username);
        var user = repository.findByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " +username+ " not found!");
        }
    }
}
