package log_processor;

import log_processor.apis.LogsApis;
import log_processor.entities.BonusApiResultEntity;
import log_processor.entities.ProcessedThreadEntity;
import log_processor.pre_processor.PreProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MainDriver {
    static DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    public static void main(String[] args) {
//        Path path = new Path("/Users/jishantsingh/playground/sandbox/src/log_processor/AssignmentLogDump/1.log");
        List<ProcessedThreadEntity> logIntervals = PreProcessor.preProcessLogs
                ("/Users/jishantsingh/playground/sandbox/src/log_processor/AssignmentLogDump",
                        5);

        System.out.println(logIntervals.size());

        LogsApis apis = new LogsApis(logIntervals);
        LocalDateTime start = LocalDateTime.parse("2020-08-09 18:59:25,276", dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse("2020-08-09 18:59:28,276", dateTimeFormatter);
        BonusApiResultEntity result = apis.bonusApi(start, end);
        System.out.println(result.maxActiveThreads + " " + result.timeOfMaxActivity.toString());


    }


}
