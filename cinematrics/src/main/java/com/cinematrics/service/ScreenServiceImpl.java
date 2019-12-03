package com.cinematrics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematrics.dao.ScreenDao;
import com.cinematrics.dto.ScreenDto;

@Service
public class ScreenServiceImpl implements ScreenService {
	@Autowired
	ScreenDao screenDao;

	@Override
	public void deleteAShowInScreen(ScreenDto dto) {

		List<ScreenDto> availableMovieInScreen = screenDao.findByMovieNameAndMovieDate(dto.getMovieName(),
				dto.getMovieDate());
		// Available shows
		List<String> availableShows = new ArrayList<>();
		// given Show to Delete
		List<String> showsToDelete = new ArrayList<>();

		availableMovieInScreen.forEach(screen -> {
			if (null != screen.getShowTimes()) {

				screen.getShowTimes().forEach(st -> {
					availableShows.add(st.getName());
				});
				;

			}
		});
		dto.getShowTimes().forEach(inputShow -> {
			showsToDelete.add(inputShow.getName());
		});

		availableMovieInScreen.forEach(screen -> {
			if (null != screen.getShowTimes()) {
				screen.getShowTimes().forEach(sh -> {
					if (showsToDelete.contains(sh.getName())) {
						if (sh.getSeats().stream().anyMatch(st -> st.isSeatStatus() == true)) {
							throw new RuntimeException("You can not delete this show as its already booked");
						}
						screen.setShowTimes(null);
						screenDao.save(screen);
					}
				});
			}

		});

		// screenDao.saveAll(availableMovieInScreen);

	}

}
