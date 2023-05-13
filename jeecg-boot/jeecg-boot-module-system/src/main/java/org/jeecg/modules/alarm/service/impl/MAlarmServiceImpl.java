package org.jeecg.modules.alarm.service.impl;

import org.jeecg.modules.alarm.entity.MAlarm;
import org.jeecg.modules.alarm.mapper.MAlarmMapper;
import org.jeecg.modules.alarm.service.IMAlarmService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
@Service
public class MAlarmServiceImpl extends ServiceImpl<MAlarmMapper, MAlarm> implements IMAlarmService {

}
