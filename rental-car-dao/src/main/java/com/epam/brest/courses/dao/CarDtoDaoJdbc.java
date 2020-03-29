package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.dto.CarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  Car DTO Service Interface implementation.
 */
@Component
public class CarDtoDaoJdbc implements CarDtoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDtoDaoJdbc.class);

    @Value("${carDto.countRent}")
    private String findAllCountRent;


    public CarDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarDto> findAllCountRent() {

        LOGGER.debug("findAllWithCountRent()");
        List<CarDto> cars = namedParameterJdbcTemplate.query(findAllCountRent,
                BeanPropertyRowMapper.newInstance(CarDto.class));
        return cars;
    }

}
