package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MealService {

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public MealTo get(int id, int userId) {
        Collection<Meal> meals = new ArrayList<>();
        meals.add(repository.get(id, userId));
        return MealsUtil.getTos(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY).get(0);
    }

    public Collection<MealTo> getAll() {
        return MealsUtil.getTos(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}