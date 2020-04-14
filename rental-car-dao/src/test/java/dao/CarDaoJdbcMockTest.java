package dao;

import com.epam.brest.courses.dao.CarDaoJdbc;
import com.epam.brest.courses.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarDaoJdbcMockTest {

    @InjectMocks
    private CarDaoJdbc carDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Car>> mapper;

    @AfterEach
    void after() {
        verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void getCars() throws SQLException {

        int id = 5;
        String name = "name";
        Car car = new Car();
        ResultSet rs = mock(ResultSet.class);
        String sql = "select";
        ReflectionTestUtils.setField(carDao, "selectSql", sql);

        when(namedParameterJdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Collections.singletonList(car));
        when(rs.getInt(anyString())).thenReturn(id);
        when(rs.getString(anyString())).thenReturn(name);

        List<Car> cars = carDao.findAll();
        assertNotNull(cars);
        assertEquals(1, cars.size());
        Car car1 = cars.get(0);
        assertNotNull(car1);
        assertSame(car1, car);

        verify(namedParameterJdbcTemplate).query(eq(sql), mapper.capture());

        RowMapper<Car> rowMapper = mapper.getValue();
        assertNotNull(rowMapper);
        Car result = rowMapper.mapRow(rs, 0);
        assertNotNull(result);
        assertEquals(id, result.getCarId().intValue());
        assertEquals(name, result.getModel());
    }

}