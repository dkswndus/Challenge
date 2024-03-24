package stuty.first.entity;

import jakarta.persistence.*;
import lombok.*;
import stuty.first.dto.UserDTO;

@Getter
@Setter
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test")
public class UserEntity {

    @Id  //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userEmail;


    @Column
    private String userId;

    @Column
    private String userPassword;

    @Column
    private String userNickname;


    public UserEntity(String userId, String userEmail, String userPassword, Long id, String userNickname) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.id = id;
            this.userPassword = userPassword;
            this.userNickname =userNickname;
    }
    public static UserEntity toUpdateUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUserPassword(userDTO.getUserPassword());
        userEntity.setUserNickname(userDTO.getUserNickname());
        return userEntity;
    }


}
