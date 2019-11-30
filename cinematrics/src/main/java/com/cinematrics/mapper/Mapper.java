package com.cinematrics.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cinematrics.dto.MovieDto;
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

}
