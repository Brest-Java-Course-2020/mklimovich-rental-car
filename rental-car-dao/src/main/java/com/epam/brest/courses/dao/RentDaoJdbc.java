package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Rent;
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

import static com.epam.brest.courses.constants.CarConstants.CAR_ID;
import static com.epam.brest.courses.constants.RentConstants.DATERENT;
import static com.epam.brest.courses.constants.RentConstants.RENT_ID;


/**
 * Rent DAO JDBC implementation.
 */
@Component
public class RentDaoJdbc implements RentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDaoJdbc.class);

    private final static String SELECT_ALL =
            "select rent_id, daterent, car_id from rent order by daterent";

    private static final String FIND_BY_ID =
            "select rent_id, daterent, car_id " +
            "from rent where rent_id = :rentId";

    private static final String FIND_BY_CAR_ID =
            "select rent_id, daterent, car_id " +
            "from rent where car_id = :carId";

    private final static String ADD_RENT =
            "insert into rent (daterent, car_id) " +
                    "values (:dateRent, :carId)";

    private static final String UPDATE =
            "update rent set daterent = :dateRent, car_id = :carId where rent_id = :rentId";

    private static final String DELETE =
            "delete from rent where rent_id = :rentId";

    public RentDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Rent> findAll() {

        LOGGER.trace("findAll()");
        List<Rent> rent =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Rent.class));
        return rent;
    }

    @Override
    public List<Rent> findByCarId(Integer carId) {

        LOGGER.trace("findByDepartmentId(departmentId:{})", carId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Rent> results = namedParameterJdbcTemplate.query(FIND_BY_CAR_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Rent.class));
        return results;
    }

    @Override
    public Optional<Rent> findById(Integer rentId) {

        LOGGER.debug("findById(rentId:{})", rentId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(RENT_ID, rentId);
        List<Rent> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Rent.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Integer create(Rent rent) {

        LOGGER.debug("create(rent:{})", rent);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DATERENT, rent.getDateRent());
        parameters.addValue(CAR_ID,rent.getCarId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_RENT, parameters, generatedKeyHolder);
        return generatedKeyHolder.getKey().intValue();
    }

    @Override
    public int update(Rent rent) {

        LOGGER.debug("update(rent:{})", rent);
        return namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(rent));
    }

    @Override
    public int delete(Integer rentId) {

        LOGGER.debug("delete(rentId:{})", rentId);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(RENT_ID, rentId);
        return namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource);
    }

}
