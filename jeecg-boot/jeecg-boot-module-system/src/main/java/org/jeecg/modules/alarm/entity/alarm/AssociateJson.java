package org.jeecg.modules.alarm.entity.alarm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssociateJson {

    private int taskId;
    private int errCode;
    private String errMsg;
    private int elapsedTimeMS;
    private Parameter parameter;
    private TaskResult taskResult;
}
