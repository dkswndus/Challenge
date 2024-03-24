package stuty.first.dto;

import lombok.*;
import stuty.first.entity.UserEntity;

@Data
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 추가
@ToString

public class UserDTO {

    private Long id;
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userNickname;




    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                '}';
    }

    // 엔티티를 DTO로 변환하는 메서드
    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setUserEmail(userEntity.getUserEmail());
        dto.setUserId(userEntity.getUserId());
        dto.setUserPassword(userEntity.getUserPassword());
        dto.setUserNickname(userEntity.getUserNickname());
        return dto;
    }

}
