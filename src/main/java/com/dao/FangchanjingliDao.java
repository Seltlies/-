package com.dao;

import com.entity.FangchanjingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FangchanjingliVO;
import com.entity.view.FangchanjingliView;


/**
 * 房产经理
 * 
 * @author 
 * @email 
 * @date 2024-02-05 14:22:24
 */
public interface FangchanjingliDao extends BaseMapper<FangchanjingliEntity> {
	
	List<FangchanjingliVO> selectListVO(@Param("ew") Wrapper<FangchanjingliEntity> wrapper);
	
	FangchanjingliVO selectVO(@Param("ew") Wrapper<FangchanjingliEntity> wrapper);
	
	List<FangchanjingliView> selectListView(@Param("ew") Wrapper<FangchanjingliEntity> wrapper);

	List<FangchanjingliView> selectListView(Pagination page,@Param("ew") Wrapper<FangchanjingliEntity> wrapper);

	
	FangchanjingliView selectView(@Param("ew") Wrapper<FangchanjingliEntity> wrapper);
	

}
