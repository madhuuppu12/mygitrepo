package com.cinematrics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinematrics.dto.MovieDto;

@Repository
public interface MovieDao extends JpaRepository<MovieDto, Long> {

}
