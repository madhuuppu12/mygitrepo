package com.cinematrics.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cinematrics.dto.Movie;

@Repository
public interface MovieDao extends MongoRepository<Movie, Long> {

}
