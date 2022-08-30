package kodlama.io.hrms.service.impl;

import kodlama.io.hrms.entities.concretes.User;
import kodlama.io.hrms.entities.dto.UserModel;
import kodlama.io.hrms.repo.UserRepo;
import kodlama.io.hrms.service.UserService;
import kodlama.io.hrms.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    @Override
    public UserModel addUser(UserModel userModel)  {
        Boolean existEmail = userRepo.selectExistEmail(userModel.getEmail());
        if (existEmail){
        return null;
        }
        userRepo.save(userMapper.checkAndConvertToEntity(userModel));
        return  userModel;
    }

    @Override
    public UserModel deleteUser(String id) {
        return null;
    }


    @Override
    public List<UserModel> getAllUser() {
        List<User> userList = userRepo.findAll();
        List<UserModel> userModelList = new ArrayList<>();
        for(User user : userList){
            userModelList.add(userMapper.convertToDto(user));
        }
        return userModelList;
    }

    @Override
    public UserModel findByEmail(String email) {
        User user = userRepo.findByEmail(email);
        UserModel userModel = userMapper.convertToDto(user);
        return userModel;
    }

}
