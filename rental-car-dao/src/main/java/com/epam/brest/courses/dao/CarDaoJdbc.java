package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.CarConstants.*;

/**
 * Car DAO JDBC implementation.
 */
@Component
public class CarDaoJdbc implements CarDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDaoJdbc.class);

    private final static String SELECT_ALL =
            "select car_id, brandCar, color from car order by brandCar, color";

    private static final String FIND_BY_ID =
            "select car_id, brandCar, color" +
                    "from car where car_id = :carId";

    private final static String ADD_CAR =
            "insert into car (brandCar, color) " +
                    "values (:brandCar, :color)";

    private static final String UPDATE =
            "update car set brandCar = :brandCar, color = :color " +
                    "where car_id = :carId";

    private static final String DELETE =
            "delete from car where car_id = :carId";



    public CarDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Car> findAll() {

        LOGGER.trace("findAll()");
        List<Car> cars =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Car.class));
        return cars;
    }

    @Override
    public Optional<Car> findById(Integer carId) {

        LOGGER.debug("findById(carId:{})", carId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Car> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Car.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Integer create(Car car) {

        LOGGER.debug("create(car:{})", car);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(BRANDCAR, car.getBrandCar());
        parameters.addValue(COLOR, car.getColor());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR, parameters, generatedKeyHolder);
        return generatedKeyHolder.getKey().intValue();
    }

    @Override
    public int update(Car car) {

        LOGGER.debug("update(car:{})", car);
        return namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(car));
    }

    @Override
    public int delete(Integer carId) {

        LOGGER.debug("delete(carId:{})", carId);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CAR_ID, carId);
        return namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource);
    }

}
