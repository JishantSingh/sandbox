package log_processor.entities;

import java.util.List;
import java.util.Objects;

public class ProcessedThreadEntity implements Comparable<ProcessedThreadEntity> {
    public RequestThreadEntity entity;
    public List<RequestThreadEntity> openThreads;

    public ProcessedThreadEntity(RequestThreadEntity entity, List<RequestThreadEntity> list) {
        this.entity = entity;
        if (entity.isStart) {
            list.add(entity);
        } else if (entity.isEnd) {
            list.remove(entity);
        }
        this.openThreads = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedThreadEntity that = (ProcessedThreadEntity) o;
        return entity.equals(that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity);
    }

    @Override
    public int compareTo(ProcessedThreadEntity o) {
        return this.entity.timestamp.compareTo(o.entity.timestamp);
    }
}
