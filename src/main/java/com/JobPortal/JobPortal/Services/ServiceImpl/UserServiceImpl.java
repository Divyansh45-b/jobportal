package com.JobPortal.JobPortal.Services.ServiceImpl;

import com.JobPortal.JobPortal.Entities.User;
import com.JobPortal.JobPortal.Helper.UserDto;
import com.JobPortal.JobPortal.Repositories.UserRepo;
import com.JobPortal.JobPortal.Services.UserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserImpl {

    @Autowired
    private ModelMapper modelMapper;


    /// constructor injection -> easy for testing , immutable dependency.
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        User saveedUser = this.userRepo.save(user);

        return this.modelMapper.map(saveedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("userId not found"));


        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setContactNumber(userDto.getContactNumber());

        User updatedUser = this.userRepo.save(user);

        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> getUsers = this.userRepo.findAll();

        List<UserDto> getAllUsers = new ArrayList<>();
        for (User u : getUsers) {
            getAllUsers.add(this.modelMapper.map(u, UserDto.class));
        }
        return getAllUsers;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("userId not found"));

        this.userRepo.delete(user);
        System.out.println("deleted successfully");
    }

    @Override
    public UserDto findByUserId(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("suerId not found"));

        return this.modelMapper.map(user, UserDto.class);
    }

}
