package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL1_ID = 100003;

    private static final int MEAL2_ID = 100004;

    private static final int MEAL3_ID = 100005;

    public static final Meal meal1 = new Meal(MEAL1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);

    public static final Meal meal2 = new Meal(MEAL2_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);

    public static final Meal meal3 = new Meal(MEAL3_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 500);

    public static Meal newMeal() {
        return new Meal(null, LocalDateTime.of(2022, Month.JANUARY, 15, 14, 30, 0), "Ланч", 430);
    }

    public static Meal getUpdated() {
        Meal meal = new Meal(meal1);
        Meal newMeal = newMeal();
        meal.setDateTime(newMeal.getDateTime());
        meal.setDescription(newMeal.getDescription());
        meal.setCalories(meal.getCalories());
        return meal;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("registered", "roles").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
