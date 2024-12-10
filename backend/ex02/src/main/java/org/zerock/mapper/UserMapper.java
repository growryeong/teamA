package org.zerock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.UserDTO;
import org.zerock.domain.User;
import org.zerock.domain.UserChallengeDTO;

@Mapper
public interface UserMapper {
   // 회원가입 관련
   void insertUser(UserDTO user);
   int checkUserId(String userId);
   int checkEmail(String email);
   
   // 로그인 관련
   UserDTO getUserByUserId(String userId);
   
   // 회원정보 수정 관련
   int updatePassword(@Param("userId") String userId, @Param("password") String password);
   void updateEmail(@Param("userId") String userId,@Param("email") String email);
   
   // 챌린지 관련
   List<UserChallengeDTO> getUserChallenges(String userId);
   User findById(String userId);
}