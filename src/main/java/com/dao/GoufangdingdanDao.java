package com.dao;

import com.entity.GoufangdingdanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GoufangdingdanVO;
import com.entity.view.GoufangdingdanView;


/**
 * 购房订单
 * 
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
public interface GoufangdingdanDao extends BaseMapper<GoufangdingdanEntity> {
	
	List<GoufangdingdanVO> selectListVO(@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);
	
	GoufangdingdanVO selectVO(@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);
	
	List<GoufangdingdanView> selectListView(@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);

	List<GoufangdingdanView> selectListView(Pagination page,@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);

	
	GoufangdingdanView selectView(@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);



}
