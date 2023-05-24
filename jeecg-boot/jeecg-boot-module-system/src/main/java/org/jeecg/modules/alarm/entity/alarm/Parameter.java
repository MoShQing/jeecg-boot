package org.jeecg.modules.alarm.entity.alarm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Parameter {
    private List<Double> thresholdList;
}
