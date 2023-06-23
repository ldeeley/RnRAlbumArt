package com.example.rnralbumart.Util;

import com.example.rnralbumart.dto.UserRequestDTO;
import com.example.rnralbumart.dto.UserResponseDTO;
import com.example.rnralbumart.model.UserEntity;

public class AppUtils {

    public static UserEntity mapDTOToEntity(UserRequestDTO userRequestDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userRequestDTO.getUserName());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setFirstName(userRequestDTO.getFirstName());
        userEntity.setLastName(userRequestDTO.getLastName());
        return userEntity;
    }

    public static UserResponseDTO mapEntityToDTO(UserEntity userEntity){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(userEntity.getUserId());
        userResponseDTO.setUserName(userEntity.getUserName());
        userResponseDTO.setPassword(userEntity.getPassword());
        userResponseDTO.setFirstName(userEntity.getFirstName());
        userResponseDTO.setLastName(userEntity.getLastName());
        return userResponseDTO;
    }



}
