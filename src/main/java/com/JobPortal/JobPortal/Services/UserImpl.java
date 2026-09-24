package com.JobPortal.JobPortal.Services;

import com.JobPortal.JobPortal.Helper.UserDto;

import java.util.List;

public interface UserImpl {

    UserDto createUser (UserDto userDto);

    UserDto updateUser (UserDto userDto, Integer userId);

    List<UserDto> getAllUser();

    void deleteUser (Integer userId);

    UserDto findByUserId(Integer userId);



}
