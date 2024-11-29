package com.entity.view;

import com.entity.KanfangxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 看房信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
@TableName("kanfangxinxi")
public class KanfangxinxiView  extends KanfangxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public KanfangxinxiView(){
	}
 
 	public KanfangxinxiView(KanfangxinxiEntity kanfangxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, kanfangxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
