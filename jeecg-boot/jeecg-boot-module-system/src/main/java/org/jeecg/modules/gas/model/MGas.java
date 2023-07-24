package org.jeecg.modules.gas.model;

import java.util.Date;

import lombok.Data;


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
  private Date updateTime;
  private long delFlag;
}
