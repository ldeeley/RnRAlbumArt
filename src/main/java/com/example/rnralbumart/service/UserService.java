package com.example.rnralbumart.service;

import com.example.rnralbumart.Util.AppUtils;
import com.example.rnralbumart.dto.UserRequestDTO;
import com.example.rnralbumart.dto.UserResponseDTO;
import com.example.rnralbumart.exception.UserServiceException;
import com.example.rnralbumart.model.UserEntity;
import com.example.rnralbumart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        UserEntity userEntity = AppUtils.mapDTOToEntity(userRequestDTO);
        UserEntity newUserEntity = null;
        try {
            newUserEntity = userRepository.save(userEntity);
            return AppUtils.mapEntityToDTO(newUserEntity);
        } catch (Exception exception){
            throw new UserServiceException(" failed to save " + userRequestDTO + "to UserRepository");
        }
    }

    public UserResponseDTO getUser(Integer userId) {
        UserEntity newUserEntity = null;
        newUserEntity = userRepository.findById(userId).orElseThrow(()->new UserServiceException("No User with UserId = "+userId+ " found"));
        return AppUtils.mapEntityToDTO(newUserEntity);
    }

    public List<UserResponseDTO> getAllUsers(){
        try {
            return StreamSupport.
                    stream(userRepository.findAll().spliterator(),false)
                    .map(AppUtils::mapEntityToDTO)
                    .collect(Collectors.toList());

        } catch (Exception exception){
            throw new UserServiceException(" failed to retrieve ALL Users from UserRepository");
        }
    }

    public String deleteUser(Integer userId) {
        try{
            userRepository.deleteById(userId);
            return "user "+userId+" successfully deleted";
        } catch (Exception exception){
            throw new UserServiceException(" failed to delete User "+userId+"  from UserRepository");
        }
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Integer userId) {

            UserEntity updatedUser = AppUtils.mapDTOToEntity(userRequestDTO);
            updatedUser.setUserId(userId);
            try {
                return AppUtils.mapEntityToDTO(userRepository.save(updatedUser));
            } catch (Exception exception){
                throw new UserServiceException(" unable to update User "+userId+"  to UserRepository");
            }
    }
}
