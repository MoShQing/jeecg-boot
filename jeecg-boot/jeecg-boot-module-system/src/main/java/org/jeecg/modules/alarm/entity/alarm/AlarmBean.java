package org.jeecg.modules.alarm.entity.alarm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AlarmBean {

    private int taskId;
    private String cameraId;
    private String cameraUrl;
    private String cameraName;
    private long timestamp;
    private int imageWidth;
    private int imageHeight;
    private String srcPicName;
    private String srcPicData;
    private String alarmPicName;
    private String alarmPicData;
    private ResultData resultData;
    private int taskGroupId;
    private List<AssociateJson> associateJson;
}
