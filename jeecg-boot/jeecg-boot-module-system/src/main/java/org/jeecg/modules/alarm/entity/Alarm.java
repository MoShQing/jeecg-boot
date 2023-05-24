package org.jeecg.modules.alarm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Alarm {
	/**报警类别*/
	private Integer taskId;
	/**摄像头ID*/
	private String cameraId;
	/**摄像头名称*/
	private String cameraName;
	/**报警图片*/
	private String srcPicData;
}
