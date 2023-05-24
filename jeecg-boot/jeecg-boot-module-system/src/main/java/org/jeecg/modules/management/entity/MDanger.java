package org.jeecg.modules.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 重点危险品工艺
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Data
@TableName("m_danger")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="m_danger对象", description="重点危险品工艺")
public class MDanger implements Serializable {

	private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private String id;
	/**sourceCode*/
	@Excel(name = "sourceCode", width = 15)
    @ApiModelProperty(value = "sourceCode")
	private String sourceCode;
	/**dangerName*/
	@Excel(name = "dangerName", width = 15)
    @ApiModelProperty(value = "dangerName")
	private String dangerName;
	/**remark*/
	@Excel(name = "remark", width = 15)
    @ApiModelProperty(value = "remark")
	private String remark;
	/**
	 * 删除状态（0，正常，1已删除）
	 */
	@Excel(name = "删除状态", width = 15,dicCode="del_flag")
	@TableLogic
	private Integer delFlag;
	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新人
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	private Date updateTime;
}
