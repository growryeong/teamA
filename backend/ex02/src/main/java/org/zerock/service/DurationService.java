package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Duration;
import org.zerock.mapper.DurationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DurationService {
    private final DurationMapper durationMapper;

    public List<Duration> getAllDurations() {
        return durationMapper.findAll();
    }
}
