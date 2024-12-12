package org.zerock.mapper;

import org.zerock.domain.Challenge;
import org.zerock.domain.ChallengeTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChallengeMapper {
    List<Challenge> findAll();
    void insert(Challenge challenge);
    Challenge findById(Long challengeId);
    Challenge findByTitleAndType(@Param("title") String title, @Param("typeName") String typeName);
    Challenge findByTitleAndType(Map<String, Object> params);
    
    Challenge findByChallengeId(@Param("challengeId") Long challengeId);
    ChallengeTask findTaskByChallengeId(@Param("challengeId") Long challengeId);
}
