package org.jeecg.modules.gas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class MGas {

  private String id;
  private String sn;
  private int gasIndex;
  private int valueFirst;
  private int valueSecond;
  private int gasType;
  private int status;
  private Date createTime;
  private String updateBy;
  private Date updateTime;
  private long delFlag;
}
