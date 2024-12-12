package org.zerock.mapper;

import org.zerock.domain.ChallengeTask;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ChallengeTaskMapper {
    List<ChallengeTask> findByChallengeId(Long challengeId);
    List<ChallengeTask> findAllTasks();
    void insert(ChallengeTask task);
}
