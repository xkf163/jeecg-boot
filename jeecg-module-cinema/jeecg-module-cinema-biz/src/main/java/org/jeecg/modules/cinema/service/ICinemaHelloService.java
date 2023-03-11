package org.jeecg.modules.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.cinema.entity.CinemaHelloEntity;

/**
 * 测试接口
 */
public interface ICinemaHelloService extends IService<CinemaHelloEntity> {

    String hello();

}
