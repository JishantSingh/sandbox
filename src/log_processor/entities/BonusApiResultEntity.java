package log_processor.entities;

import log_processor.entities.BasicApiResultEntity;

import java.time.LocalDateTime;
import java.util.List;

public class BonusApiResultEntity {
    List<BasicApiResultEntity> activeThreads;
    public int maxActiveThreads;
    public LocalDateTime timeOfMaxActivity;
    public BonusApiResultEntity(List<BasicApiResultEntity> activeThreads, int maxActiveThreads, LocalDateTime timeOfMaxActivity){
        this.activeThreads = activeThreads;
        this.maxActiveThreads = maxActiveThreads;
        this.timeOfMaxActivity = timeOfMaxActivity;
    }
}
