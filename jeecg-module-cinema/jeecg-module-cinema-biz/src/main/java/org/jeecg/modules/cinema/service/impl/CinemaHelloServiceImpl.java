package org.jeecg.modules.cinema.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.cinema.entity.CinemaHelloEntity;
import org.jeecg.modules.cinema.mapper.CinemaHelloMapper;
import org.jeecg.modules.cinema.service.ICinemaHelloService;
import org.springframework.stereotype.Service;

/**
 * 测试Service
 */
@Service
public class CinemaHelloServiceImpl extends ServiceImpl<CinemaHelloMapper, CinemaHelloEntity> implements ICinemaHelloService {

    @Override
    public String hello() {
        return "hello ，我是 cinema 微服务节点!";
    }
}
