package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

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

    public Meal get(int id) {
        return repository.get(id, SecurityUtil.authUserId());
    }

    public Collection<MealTo> getAll() {
        return MealsUtil.getTos(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}