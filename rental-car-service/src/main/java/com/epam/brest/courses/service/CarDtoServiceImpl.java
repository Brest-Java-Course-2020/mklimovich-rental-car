package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.CarDtoDao;

import com.epam.brest.courses.model.dto.CarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarDtoServiceImpl implements CarDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDtoServiceImpl.class);

    private final CarDtoDao carDtoDao;

    public CarDtoServiceImpl(CarDtoDao carDtoDao) {
        this.carDtoDao = carDtoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDto> findAllCountRent() {

        LOGGER.debug("findAllCountRent()");
        return carDtoDao.findAllCountRent();
    }
}
