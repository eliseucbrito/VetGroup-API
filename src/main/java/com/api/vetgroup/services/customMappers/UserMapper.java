package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.models.Role;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User convertStaffToUser(StaffUser staff) {
        User user = new User();

        String[] nameArray = staff.getFull_name().split(" ");
        String userName = nameArray[0] + " " + nameArray[nameArray.length - 1];

        user.setId(staff.getId());
        user.setUserName(userName);
        user.setFullName(staff.getFull_name());
        user.setPassword(passwordEncoder.encode(staff.getPassword()).substring("{pbkdf2}".length()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Role role = new Role();
        role.setId(1L);
        role.setDescription("CEO");

        List<Role> rolesList = new ArrayList<>();
        rolesList.add(role);
        user.setRoles(rolesList);

        return user;
    }
}
