package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.LoginDTO;
import com.jobportal.polaris_backend.dto.ResponseDTO;
import com.jobportal.polaris_backend.dto.UserDTO;
import com.jobportal.polaris_backend.entity.OTPEntity;
import com.jobportal.polaris_backend.entity.UserEntity;
import com.jobportal.polaris_backend.exception.JobPortalException;
import com.jobportal.polaris_backend.repository.IUserRepository;

import com.jobportal.polaris_backend.repository.IOTPRepository;
import com.jobportal.polaris_backend.utility.Data;
import com.jobportal.polaris_backend.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IOTPRepository iotpRepository;

    @Autowired
    private IProfileService iProfileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<UserEntity> optional = iUserRepository.findByEmail(userDTO.getEmail());
        if (optional.isPresent()) throw new JobPortalException("USER_FOUND");
        userDTO.setProfileId(iProfileService.createProfile(userDTO.getEmail()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity user = userDTO.toEntity();
        user = iUserRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        UserEntity user = iUserRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new JobPortalException("INVALID_CREDENTIALS");
        return user.toDTO();
    }

    @Override
    public Boolean sendOtp(String email) throws Exception {
        UserEntity user = iUserRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Підтвердіть свій аккаунт");
        String genOtp = Utilities.generateOTP();
        OTPEntity otp = new OTPEntity(email, genOtp, LocalDateTime.now());
        iotpRepository.save(otp);
        message.setText(Data.getMessageBody(genOtp, user.getName()), true);
        mailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTPEntity otpEntity = iotpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP_NOT_FOUND"));
        if(!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP_INCORRECT");
        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        UserEntity user = iUserRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        iUserRepository.save(user);
        return new ResponseDTO("Пароль успішно змінено");
    }

    @Scheduled(fixedRate = 600000)
    public void removeExpiredOTP() {
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(10);
        List<OTPEntity>expiredOTPs=iotpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOTPs.isEmpty()){
            iotpRepository.deleteAll(expiredOTPs);
            System.out.println("Removed " + expiredOTPs.size() + " expired OTPs.");
        }
    }

}
