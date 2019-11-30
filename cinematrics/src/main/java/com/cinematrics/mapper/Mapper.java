package com.cinematrics.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.TimeDto;
import com.cinematrics.vo.MovieVo;

@Component
public class Mapper {

	public MovieDto convertVotoDto(MovieVo vo) {
		MovieDto dto = new MovieDto();
		BeanUtils.copyProperties(vo, dto);
		return dto;

	}

	public MovieVo convertDtotoVo(MovieDto dto) {

		MovieVo vo = new MovieVo();

		BeanUtils.copyProperties(dto, vo);
		return vo;
	}

	public MovieDto convertMovieVotoDto(MovieVo vo) {
	
		MovieDto movie=new MovieDto();
		List<TimeDto> list=new ArrayList<>();
		TimeDto  timings=new TimeDto();
		
		
		return null;
	}
}
