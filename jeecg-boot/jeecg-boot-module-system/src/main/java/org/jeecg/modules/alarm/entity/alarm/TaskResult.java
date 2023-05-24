package org.jeecg.modules.alarm.entity.alarm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TaskResult {

    private int classId;
    private int score;
    private List<ObjectList> objectList;
}
