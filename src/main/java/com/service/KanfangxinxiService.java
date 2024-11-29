package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KanfangxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KanfangxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KanfangxinxiView;


/**
 * 看房信息
 *
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
public interface KanfangxinxiService extends IService<KanfangxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KanfangxinxiVO> selectListVO(Wrapper<KanfangxinxiEntity> wrapper);
   	
   	KanfangxinxiVO selectVO(@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);
   	
   	List<KanfangxinxiView> selectListView(Wrapper<KanfangxinxiEntity> wrapper);
   	
   	KanfangxinxiView selectView(@Param("ew") Wrapper<KanfangxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KanfangxinxiEntity> wrapper);

   	

}

