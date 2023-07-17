package org.jeecg.modules.neazj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.neazj.entity.NeazjHelloEntity;

/**
 * 测试接口
 */
public interface INeazjHelloService extends IService<NeazjHelloEntity> {

    String hello();

}
