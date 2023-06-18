package org.jeecg.modules.gas.service.impl;

import org.jeecg.modules.alarm.entity.MAlarm;
import org.jeecg.modules.alarm.mapper.MAlarmMapper;
import org.jeecg.modules.alarm.service.IMAlarmService;
import org.jeecg.modules.gas.mapper.MGasMapper;
import org.jeecg.modules.gas.model.MGas;
import org.jeecg.modules.gas.service.MGasService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
@Service
public class MGasServiceImpl extends ServiceImpl<MGasMapper, MGas> implements MGasService {
    @Resource
    private MGasMapper mGasMapper;

    public boolean saveOrUpdateBatch(Collection<MGas> entityList) {
        for (MGas mGas : entityList) {
            mGasMapper.saveOrUpdateBatch(mGas);
        }
        return true;
    }
}
