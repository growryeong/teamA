package org.zerock.mapper;

import org.zerock.domain.Duration;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DurationMapper {
    List<Duration> findAll();
}