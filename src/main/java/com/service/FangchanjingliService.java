package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FangchanjingliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.FangchanjingliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.FangchanjingliView;


/**
 * 房产经理
 *
 * @author 
 * @email 
 * @date 2024-02-05 14:22:24
 */
public interface FangchanjingliService extends IService<FangchanjingliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FangchanjingliVO> selectListVO(Wrapper<FangchanjingliEntity> wrapper);
   	
   	FangchanjingliVO selectVO(@Param("ew") Wrapper<FangchanjingliEntity> wrapper);
   	
   	List<FangchanjingliView> selectListView(Wrapper<FangchanjingliEntity> wrapper);
   	
   	FangchanjingliView selectView(@Param("ew") Wrapper<FangchanjingliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FangchanjingliEntity> wrapper);

   	

}

