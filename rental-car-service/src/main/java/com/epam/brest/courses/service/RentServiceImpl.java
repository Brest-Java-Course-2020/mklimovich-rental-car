package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.RentDao;
import com.epam.brest.courses.model.Rent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentServiceImpl implements RentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentServiceImpl.class);

    private final RentDao rentDao;

    public RentServiceImpl(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rent> findAll() {
        LOGGER.trace("findAll()");
        return rentDao.findAll();
    }

    @Override
    public List<Rent> findByCarId(Integer carId) {
        LOGGER.trace("findByDepartmentId(departmentId:{})", carId);
        return rentDao.findAll();
    }

    @Override
    public Optional<Rent> findById(Integer employeeId) {
        LOGGER.debug("findById(id:{})", employeeId);
        return rentDao.findById(employeeId);
    }

    @Override
    public Integer create(Rent rent) {
        LOGGER.debug("create(rent:{})", rent);
        return rentDao.create(rent);
    }

    @Override
    public int update(Rent rent) {
        LOGGER.debug("update(rent:{})", rent);
        return rentDao.update(rent);
    }

    @Override
    public int delete(Integer rentId) {
        LOGGER.debug("delete(rentId:{})", rentId);
        return rentDao.delete(rentId);
    }
}
