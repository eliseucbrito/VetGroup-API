package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.StaffCreateDto;
import com.api.vetgroup.models.Role;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.User;
import com.api.vetgroup.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User convertStaffDtoToUser(StaffCreateDto staffDto) {
        User user = new User();

        String[] nameArray = staffDto.getFullName().split(" ");
        String userName = nameArray[0] + " " + nameArray[nameArray.length - 1];

        Role role = roleRepository.findByDescription(staffDto.getRole());
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        user.setUserName(staffDto.getEmail());
        user.setFullName(staffDto.getFullName());
        user.setPassword(passwordEncoder.encode(staffDto.getPassword()).substring("{pbkdf2}".length()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setRoles(roleList);

        return user;
    }
}
