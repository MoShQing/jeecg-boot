package org.jeecg.modules.demo.enterprise.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 企业基本信息
 * @Author: jeecg-boot
 * @Date:   2023-05-13
 * @Version: V1.0
 */
@Data
@TableName("mm_enterprise")
public class MmEnterprise implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    private java.lang.String sysOrgCode;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    private java.lang.String enterpriseName;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    private java.lang.String creditCode;
	/**法定代表人*/
	@Excel(name = "法定代表人", width = 15)
    private java.lang.String legalPerson;
	/**单位联系方式*/
	@Excel(name = "单位联系方式", width = 15)
    private java.lang.String enterprisePhone;
	/**注册地址*/
	@Excel(name = "注册地址", width = 15)
    private java.lang.String address;
	/**企业编码*/
	@Excel(name = "企业编码", width = 15)
    private java.lang.String enterpriseCode;
	/**从业人数*/
	@Excel(name = "从业人数", width = 15)
    private java.lang.String staffCount;
	/**单位负责人*/
	@Excel(name = "单位负责人", width = 15)
    private java.lang.String charger;
	/**负责人手机号*/
	@Excel(name = "负责人手机号", width = 15)
    private java.lang.String chargerPhone;
	/**安全负责人*/
	@Excel(name = "安全负责人", width = 15)
    private java.lang.String safetyManager;
	/**安全负责人手机号*/
	@Excel(name = "安全负责人手机号", width = 15)
    private java.lang.String safetyPhone;
	/**单位规模*/
	@Excel(name = "单位规模", width = 15)
    private java.lang.String companySize;
	/**成立时间*/
	@Excel(name = "成立时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date establishedTime;
	/**营业状态*/
	@Excel(name = "营业状态", width = 15)
    private java.lang.String businessStatus;
	/**安全生产标准化等级*/
	@Excel(name = "安全生产标准化等级", width = 15)
    private java.lang.String level;
	/**公司类型*/
	@Excel(name = "公司类型", width = 15)
    private java.lang.String companyType;
	/**行业类型*/
	@Excel(name = "行业类型", width = 15)
    private java.lang.String industryType;
	/**监管行业类型*/
	@Excel(name = "监管行业类型", width = 15)
    private java.lang.String manageType;
	/**监管副行业类型*/
	@Excel(name = "监管副行业类型", width = 15)
    private java.lang.String manageSecondType;
	/**行政区划分*/
	@Excel(name = "行政区划分", width = 15)
    private java.lang.String region;
	/**经营范围*/
	@Excel(name = "经营范围", width = 15)
    private java.lang.String businessScope;
}
