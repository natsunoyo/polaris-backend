package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.LoginDTO;
import com.jobportal.polaris_backend.dto.ResponseDTO;
import com.jobportal.polaris_backend.dto.UserDTO;
import com.jobportal.polaris_backend.exception.JobPortalException;


public interface IUserService {
    UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

    UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

    Boolean sendOtp(String email) throws Exception;

    Boolean verifyOtp(String email, String otp) throws JobPortalException;

    ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException;
}
