package org.jeecg.modules.cinema.entity;

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

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 电影信息
 * @Author: jeecg-boot
 * @Date:   2023-03-11
 * @Version: V1.0
 */
@Data
@TableName("fly_cinema_film")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="fly_cinema_film对象", description="电影信息")
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**导演*/
	@Excel(name = "导演", width = 15)
    @ApiModelProperty(value = "导演")
    private String directors;
	/**编剧*/
	@Excel(name = "编剧", width = 15)
    @ApiModelProperty(value = "编剧")
    private String writers;
	/**主演*/
	@Excel(name = "主演", width = 15)
    @ApiModelProperty(value = "主演")
    private String stars;
	/**IMDB编号*/
	@Excel(name = "IMDB编号", width = 15)
    @ApiModelProperty(value = "IMDB编号")
    private String imdbNo;
	/**IMDB评分*/
	@Excel(name = "IMDB评分", width = 15)
    @ApiModelProperty(value = "IMDB评分")
    private Double imdbRating;
	/**上映日期*/
	@Excel(name = "上映日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上映日期")
    private Date releaseDates;
	/**分级*/
	@Excel(name = "分级", width = 15)
    @ApiModelProperty(value = "分级")
    private String certification;
	/**类别*/
	@Excel(name = "类别", width = 15)
    @ApiModelProperty(value = "类别")
    private String genres;
	/**片名*/
	@Excel(name = "片名", width = 15)
    @ApiModelProperty(value = "片名")
    private String name;
	/**又名*/
	@Excel(name = "又名", width = 15)
    @ApiModelProperty(value = "又名")
    private String nameAka;
	/**年度*/
	@Excel(name = "年度", width = 15)
    @ApiModelProperty(value = "年度")
    private Integer year;
	/**片长*/
	@Excel(name = "片长", width = 15)
    @ApiModelProperty(value = "片长")
    private Integer runtime;
	/**国家*/
	@Excel(name = "国家", width = 15)
    @ApiModelProperty(value = "国家")
    private String country;
	/**简介*/
	@Excel(name = "简介", width = 15)
    @ApiModelProperty(value = "简介")
    private String introduce;
}
