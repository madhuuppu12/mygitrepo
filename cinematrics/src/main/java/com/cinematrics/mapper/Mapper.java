package com.cinematrics.mapper;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.aspectj.weaver.patterns.ConcreteCflowPointcut.Slot;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.dto.TimeDto;
import com.cinematrics.vo.MovieVo;
import com.cinematrics.vo.ScreenVo;

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

	public ScreenDto convertSlotVotoDto(ScreenVo vo, List<LocalDateTime> dates) {

		ScreenDto screen = new ScreenDto();
		List<TimeDto> list = new ArrayList<>();

		dates.forEach(time -> {
			TimeDto timings = new TimeDto();
			timings.setStartDate(time);
			timings.setMovieName(vo.getMovieName());
			timings.setTimings(vo.getTimings());
			timings.setStatus("INACTIVE");
			list.add(timings);
		});
		screen.setScreenName(vo.getScreenName());
		screen.setTimeDto(list);

		return screen;
	}
}
