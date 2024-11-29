package com.dao;

import com.entity.KanfangxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KanfangxinxiVO;
import com.entity.view.KanfangxinxiView;


/**
 * 看房信息
 * 
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
public interface KanfangxinxiDao extends BaseMapper<KanfangxinxiEntity> {
	
	List<KanfangxinxiVO> selectListVO(@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);
	
	KanfangxinxiVO selectVO(@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);
	
	List<KanfangxinxiView> selectListView(@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);

	List<KanfangxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);

	
	KanfangxinxiView selectView(@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);
	

}
