package org.jeecg.modules.alarm.entity.alarm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Rect {

    private int x;
    private int y;
    private int width;
    private int height;
}
