package dao;

import com.epam.brest.courses.dao.CarDao;
import com.epam.brest.courses.model.Car;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.CarConstants.CAR_COLOR_SIZE;
import static com.epam.brest.courses.constants.CarConstants.CAR_MODEL_SIZE;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class CarDaoJdbcIT {

    private final CarDao carDao;

    @Autowired
    public CarDaoJdbcIT(CarDao carDao) {
        this.carDao = carDao;
    }

    @Test
    public void shouldFindAllDepartments() {

        List<Car> cars = carDao.findAll();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void shouldFindCarById() {

        // given
        Car car = new Car()
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE))
                .setColor(RandomStringUtils.randomAlphabetic(CAR_COLOR_SIZE));
        Integer id = carDao.create(car);

        // when
        Optional<Car> optionalCar = carDao.findById(id);

        // then
        Assertions.assertTrue(optionalCar.isPresent());
        assertEquals(optionalCar.get().getCarId(), id);
        assertEquals(optionalCar.get().getModel(), car.getModel());
    }

    @Test
    public void shouldCreateCar() {
        Car car = new Car()
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE))
                .setColor(RandomStringUtils.randomAlphabetic(CAR_COLOR_SIZE));
        Integer id = carDao.create(car);
        assertNotNull(id);
    }

    @Test
    public void shouldUpdateCar() {

        // given
        Car car = new Car()
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE))
                .setColor(RandomStringUtils.randomAlphabetic(CAR_COLOR_SIZE));
        Integer id = carDao.create(car);
        assertNotNull(id);

        Optional<Car> carOptional = carDao.findById(id);
        Assertions.assertTrue(carOptional.isPresent());

        carOptional.get().
                setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE)).
                setColor(RandomStringUtils.randomAlphabetic(CAR_COLOR_SIZE));

        // when
        int result = carDao.update(carOptional.get());

        // then
        assertTrue(1 == result);

        Optional<Car> updatedCarOptional = carDao.findById(id);
        Assertions.assertTrue(updatedCarOptional.isPresent());
        assertEquals(updatedCarOptional.get().getCarId(), id);
        assertEquals(updatedCarOptional.get().getModel(),carOptional.get().getModel());
        //assertEquals(updatedCarOptional.get().getColor(),carOptional.get().getColor());
    }

    @Test
    public void shouldDeleteCar() {
        // given
        Car car = new Car()
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE))
                .setColor(RandomStringUtils.randomAlphabetic(CAR_COLOR_SIZE));
        Integer id = carDao.create(car);

        List<Car> cars = carDao.findAll();
        assertNotNull(cars);

        // when
        int result = carDao.delete(id);

        // then
        assertTrue(1 == result);

        List<Car> currentCars = carDao.findAll();
        assertNotNull(currentCars);

        assertTrue(cars.size()-1 == currentCars.size());
    }

}