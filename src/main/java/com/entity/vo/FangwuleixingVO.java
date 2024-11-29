package com.entity.vo;

import com.entity.FangwuleixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 房屋类型
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2024-02-05 14:22:24
 */
public class FangwuleixingVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * image
	 */
	
	private String image;
				
	
	/**
	 * 设置：image
	 */
	 
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * 获取：image
	 */
	public String getImage() {
		return image;
	}
			
}
