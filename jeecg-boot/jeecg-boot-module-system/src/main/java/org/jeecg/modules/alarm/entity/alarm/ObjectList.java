package org.jeecg.modules.alarm.entity.alarm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ObjectList {

    private int classId;
    private double score;
    private List<Double> scoreList;
    private Rect rect;
    private List<String> polygonList;
    private List<String> multiPointList;
}
