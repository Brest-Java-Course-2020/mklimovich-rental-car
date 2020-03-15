package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.CarDao;
import com.epam.brest.courses.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        LOGGER.trace("findAll()");
        return carDao.findAll();
    }


    @Override
    public Optional<Car> findById(Integer carId) {
        LOGGER.debug("findById(id:{})", carId);
        return carDao.findById(carId);
    }

    @Override
    public Integer create(Car car) {
        LOGGER.debug("create(car:{})", car);
        return carDao.create(car);
    }

    @Override
    public int update(Car car) {
        LOGGER.debug("update(car:{})", car);
        return carDao.update(car);
    }

    @Override
    public int delete(Integer carId) {
        LOGGER.debug("delete(carId:{})", carId);
        return carDao.delete(carId);
    }
}
