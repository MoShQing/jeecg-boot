package org.jeecg.modules.gas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.gas.model.MGas;

import java.util.Collection;

/**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
public interface MGasService extends IService<MGas> {
    public boolean saveOrUpdateBatch(Collection<MGas> entityList);
}
