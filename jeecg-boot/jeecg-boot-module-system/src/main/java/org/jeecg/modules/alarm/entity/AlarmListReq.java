package org.jeecg.modules.alarm.entity;

import lombok.Data;

@Data
public class AlarmListReq  extends MAlarm{
    private String createTime_begin;
    private String createTime_end;
}
