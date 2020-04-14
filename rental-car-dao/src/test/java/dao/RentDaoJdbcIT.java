package dao;


import com.epam.brest.courses.dao.RentDao;
import com.epam.brest.courses.model.Rent;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.RentConstants.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class RentDaoJdbcIT {

    private final RentDao rentDao;

    @Autowired
    public RentDaoJdbcIT(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    @Test
    public void shouldFindAllRents() {

        List<Rent> rents = rentDao.findAll();
        assertNotNull(rents);
        assertTrue(rents.size() > 0);
    }

    @Test
    public void shouldFindRentsByCarId() {

        List<Rent> rents = rentDao.findByCarId(1);
        assertNotNull(rents);
        assertTrue(rents.size() > 0);
    }

    @Test
    public void shouldFindRentById() {

        // given
        Rent rent = new Rent()
                .setDateRent(RandomStringUtils.randomAlphabetic(RENT_DATE_RENT_SIZE))
                .setCarId(1);
        Integer id = rentDao.create(rent);

        // when
        Optional<Rent> optionalRent = rentDao.findById(id);

        // then
        Assertions.assertTrue(optionalRent.isPresent());
        assertEquals(optionalRent.get().getRentId(), id);
        assertEquals(optionalRent.get().getDateRent(), rent.getDateRent());

    }

    @Test
    public void shouldCreateRent() {
        Rent rent = new Rent()
                .setDateRent(RandomStringUtils.randomAlphabetic(RENT_DATE_RENT_SIZE))
                .setCarId(1);
        Integer id = rentDao.create(rent);
        assertNotNull(id);
    }

    @Test
    public void shouldUpdateRent() {

        // given
        Rent rent = new Rent()
                .setDateRent(RandomStringUtils.randomAlphabetic(RENT_DATE_RENT_SIZE))
                .setCarId(1);
        Integer id = rentDao.create(rent);
        assertNotNull(id);

        Optional<Rent> rentOptional = rentDao.findById(id);
        Assertions.assertTrue(rentOptional.isPresent());

        rentOptional.get().
                setDateRent(RandomStringUtils.randomAlphabetic(RENT_DATE_RENT_SIZE));

        // when
        int result = rentDao.update(rentOptional.get());

        // then
        assertTrue(1 == result);

        Optional<Rent> updatedRentOptional = rentDao.findById(id);
        Assertions.assertTrue(updatedRentOptional.isPresent());
        assertEquals(updatedRentOptional.get().getRentId(), id);
        assertEquals(updatedRentOptional.get().getDateRent(), rentOptional.get().getDateRent());

    }

    @Test
    public void shouldDeleteRent() {
        // given
        Rent rent = new Rent()
                .setDateRent(RandomStringUtils.randomAlphabetic(RENT_DATE_RENT_SIZE))
                .setCarId(1);
        Integer id = rentDao.create(rent);

        List<Rent> rents = rentDao.findAll();
        assertNotNull(rents);

        // when
        int result = rentDao.delete(id);

        // then
        assertTrue(1 == result);

        List<Rent> currentRents = rentDao.findAll();
        assertNotNull(currentRents);

        assertTrue(rents.size()-1 == currentRents.size());
    }

}