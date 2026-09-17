package com.JobPortal.JobPortal.Controllers;

import com.JobPortal.JobPortal.Helper.UserDto;


import com.JobPortal.JobPortal.Services.UserImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


     private final UserImpl userimpl;

     public UserController(UserImpl userimpl) {
        this.userimpl = userimpl;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto)
    {
        UserDto userDto1 = this.userimpl.createUser(userDto);
        return ResponseEntity.ok(userDto1);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto,@PathVariable Integer userId)
    {
        UserDto userDto1 = this.userimpl.updateUser(userDto,userId);

        return  ResponseEntity.ok(userDto1);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> allUser = this.userimpl.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId)
    {
        this.userimpl.deleteUser(userId);
        return ResponseEntity.ok("deleted user successfully");
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<UserDto> findByUserId(@PathVariable Integer userId)
    {
        UserDto userDto = this.userimpl.findByUserId(userId);
        return ResponseEntity.ok(userDto);
    }
}
