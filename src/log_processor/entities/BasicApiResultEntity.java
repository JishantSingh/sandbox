package log_processor.entities;

public class BasicApiResultEntity {
    String threadId;
    int processId;
    String logFile;

    public BasicApiResultEntity(String threadId, int processId) {
        this.threadId = threadId;
        this.processId = processId;
        this.logFile = "/Users/jishantsingh/playground/sandbox/src/log_processor/output_logs/" + threadId + ".log";
    }
}
