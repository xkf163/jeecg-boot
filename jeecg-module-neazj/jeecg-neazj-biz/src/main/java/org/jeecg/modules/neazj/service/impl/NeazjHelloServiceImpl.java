package org.jeecg.modules.neazj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.neazj.entity.NeazjHelloEntity;
import org.jeecg.modules.neazj.mapper.NeazjHelloMapper;
import org.jeecg.modules.neazj.service.INeazjHelloService;
import org.springframework.stereotype.Service;

/**
 * 测试Service
 */
@Service
public class NeazjHelloServiceImpl extends ServiceImpl<NeazjHelloMapper, NeazjHelloEntity> implements INeazjHelloService {

    @Override
    public String hello() {
        return "hello ，我是 neazj 微服务节点!";
    }
}
