package org.jeecg.modules.cinema.generator.service.impl;

import org.jeecg.modules.cinema.generator.entity.Star;
import org.jeecg.modules.cinema.generator.mapper.StarMapper;
import org.jeecg.modules.cinema.generator.service.IStarService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 演员信息
 * @Author: jeecg-boot
 * @Date:   2023-03-11
 * @Version: V1.0
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements IStarService {

}
