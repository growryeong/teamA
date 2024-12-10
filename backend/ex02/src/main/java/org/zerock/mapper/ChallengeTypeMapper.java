package org.zerock.mapper;

import org.zerock.domain.ChallengeType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChallengeTypeMapper {
    ChallengeType findByName(@Param("name") String name);
}