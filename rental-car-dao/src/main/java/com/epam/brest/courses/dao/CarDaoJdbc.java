package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.brest.courses.constants.CarConstants.*;

/**
 * Car DAO JDBC implementation.
 */
@Component
public class CarDaoJdbc implements CarDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDaoJdbc.class);

    @Value("${car.select}")
    private String selectSql;

    @Value("${car.create}")
    private String createSql;

    @Value("${car.update}")
    private String updateSql;

    @Value("${car.findById}")
    private String findByIdSql;

    @Value("${car.delete}")
    private String deleteSql;

    private final CarRowMapper carRowMapper = new CarRowMapper();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Car> findAll() {

        LOGGER.trace("findAll()");
        return namedParameterJdbcTemplate.query(selectSql, carRowMapper);
    }

    @Override
    public Optional<Car> findById(Integer carId) {

        LOGGER.debug("findById(carId:{})", carId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Car> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters, carRowMapper);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Integer create(Car car) {

        LOGGER.debug("create(car:{})", car);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(MODEL, car.getModel());
        params.addValue(COLOR, car.getColor());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createSql, params, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(Car car) {

        LOGGER.debug("update(car:{})", car);
        Map<String, Object> params = new HashMap<>();
        params.put(CAR_ID, car.getCarId());
        params.put(MODEL, car.getModel());
        params.put(COLOR, car.getColor());
        return namedParameterJdbcTemplate.update(updateSql, params);
    }

    @Override
    public int delete(Integer carId) {

        LOGGER.debug("delete(carId:{})", carId);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CAR_ID, carId);
        return namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource);
    }

    private class CarRowMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt(COLUMN_CAR_ID));
            car.setModel(resultSet.getString(COLUMN_CAR_MODEL));
            car.setColor(resultSet.getString(COLUMN_CAR_COLOR));
            return car;
        }
    }
}
