package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService service;

    @Test
    public void get() {
        assertMatch(service.get(MEAL1_ID, 100000), meal1);
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, 100000);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, 100000));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(LocalDate.of(2020, Month.JANUARY, 31), LocalDate.of(2020, Month.JANUARY, 31), 100000);
        assertMatch(meals, meal3, meal2);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(100000);
        assertMatch(meals, meal3, meal2, meal1);
    }

    @Test
    public void update() {
        Meal meal = getUpdated();
        Integer id = meal.getId();
        service.update(meal, 100000);
        assertMatch(service.get(id, 100000), meal);
    }

    @Test
    public void create() {
        Meal meal = newMeal();
        Meal created = service.create(meal, 100000);
        assertMatch(created, meal);
        assertMatch(service.get(created.getId(), 100000), meal);
    }
}