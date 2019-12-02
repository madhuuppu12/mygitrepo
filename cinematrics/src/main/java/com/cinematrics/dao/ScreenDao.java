package com.cinematrics.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cinematrics.dto.ScreenDto;
@Repository
public interface ScreenDao extends MongoRepository<ScreenDto, Long>{

}
