package log_processor.apis;

import log_processor.entities.BasicApiResultEntity;
import log_processor.entities.BonusApiResultEntity;
import log_processor.entities.ProcessedThreadEntity;
import log_processor.entities.RequestThreadEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TTTTTTTTFFFFFFFFF
 *
 * pred()
 */

public class LogsApis {
    List<ProcessedThreadEntity> logTimeLine;

    public LogsApis(List<ProcessedThreadEntity> logTimeLine) {
        this.logTimeLine = logTimeLine;
    }

    public BonusApiResultEntity bonusApi(LocalDateTime startTime, LocalDateTime endTime) {
        ProcessedThreadEntity startDummyEntity = new ProcessedThreadEntity(
                new RequestThreadEntity("", 0, true, startTime), new ArrayList<>());

        int startIndex = Collections.binarySearch(logTimeLine, startDummyEntity);
        if (startIndex < 0) {
            startIndex = -(startIndex + 1);
        }
        System.out.println(startIndex);
        LocalDateTime timeOfMaxActivity = startTime;
        List<BasicApiResultEntity> basicApiOutput = logTimeLine.get(startIndex).openThreads.stream()
                .map((x) -> new BasicApiResultEntity(x.threadId, x.processId))
                .collect(Collectors.toList());
        int maxActiveApis = basicApiOutput.size();
        for (int i = startIndex + 1; i < logTimeLine.size() && logTimeLine.get(i).entity.timestamp.compareTo(endTime) <= 0; i++) {
            RequestThreadEntity thisEntity = logTimeLine.get(i).entity;
            if (thisEntity.isStart) {
                basicApiOutput.add(new BasicApiResultEntity(thisEntity.threadId, thisEntity.processId));
            }
            if (maxActiveApis < logTimeLine.get(i).openThreads.size()) {
                maxActiveApis = logTimeLine.get(i).openThreads.size();
                timeOfMaxActivity = thisEntity.timestamp;
            }
        }
        return new BonusApiResultEntity(basicApiOutput, maxActiveApis, timeOfMaxActivity);
    }


}
