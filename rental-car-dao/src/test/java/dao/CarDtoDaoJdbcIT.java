package dao;


import com.epam.brest.courses.dao.CarDtoDao;
import com.epam.brest.courses.model.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
class CarDtoDaoJdbcIT {

    @Autowired
    CarDtoDao carDtoDao;

    @Test
    public void shouldFindAllCountRent() {
        List<CarDto> cars = carDtoDao.findAllCountRent();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
        assertTrue(cars.get(0).getCountRent().intValue() > 0);
    }
}