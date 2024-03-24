package stuty.first.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import stuty.first.dto.UserDTO;
import stuty.first.entity.UserEntity;
import stuty.first.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 주입받아야하는 final 필드에 대한 생성자를 생성
@Transactional

public class MainService {
    private final UserRepository userRepository;

    public void register(UserDTO request) {
        // DTO를 엔티티로 변환
        UserEntity userEntity = UserEntity.builder()
                .id(request.getId())
                .userEmail(request.getUserEmail())
                .userId(request.getUserId())
                .userPassword(request.getUserPassword())
                .userNickname(request.getUserNickname())
                .build();

        // userRepository를 사용하여 엔티티를 저장
        userRepository.save(userEntity);
    }

    public Optional<UserDTO> login(UserDTO request) {
        Optional<UserEntity> byUserID = userRepository.findByUserId(request.getUserId());
        if (byUserID.isPresent()) {
            // 해당 아이디를 가진 회원 정보가 있다
            UserEntity userEntity = byUserID.get();
            if (userEntity.getUserPassword().equals(request.getUserPassword())) {
                // 비밀번호 일치
                // 엔티티를 DTO로 변환하여 반환
                return Optional.of(UserDTO.toUserDTO(userEntity));
            } else {
                // 비밀번호 불일치
                return Optional.empty();
            }
        } else {
            // 해당 아이디를 가진 회원 정보가 없다.
            return Optional.empty();
        }
    }



    public UserDTO findByUserId(String useriId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(useriId);
        if(optionalUserEntity.isPresent()){
            return UserDTO.toUserDTO(optionalUserEntity.get());
        }
        else{
            return null;
        }

    }

    public UserDTO updateForm(String userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(userId);
        if(optionalUserEntity.isPresent()){
            return UserDTO.toUserDTO(optionalUserEntity.get());

        }else{
            return null;
        }

    }

    public void update(UserDTO userDTO) {
        userRepository.save(UserEntity.toUpdateUserEntity(userDTO));
    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }



}

