package log_processor.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class RequestThreadEntity implements Comparable<RequestThreadEntity> {
    public String threadId;
    public int processId;
    public boolean isStart;
    public boolean isEnd;
    public LocalDateTime timestamp;

    public RequestThreadEntity(String threadId, int processId, boolean isStart, LocalDateTime timeString) {
        this.threadId = threadId;
        this.processId = processId;
        this.isStart = isStart;
        this.timestamp = timeString;
        this.isEnd = !isStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestThreadEntity entity = (RequestThreadEntity) o;
        return timestamp.equals(entity.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }

    @Override
    public int compareTo(RequestThreadEntity o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
