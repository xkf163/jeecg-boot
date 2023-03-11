package org.jeecg.modules.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.cinema.entity.Film;
import org.jeecg.modules.cinema.mapper.FilmMapper;
import org.jeecg.modules.cinema.service.IFilmService;
import org.springframework.stereotype.Service;

@Service
public class IFilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements IFilmService {
}
