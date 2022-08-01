package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal save(Meal meal) {
        log.info("save {}", meal);
        return service.save(meal);
    }

    public boolean delete(int id) {
        log.info("delete {}", id);
        return service.delete(id);
    }

    public MealTo get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public Collection<MealTo> getAll() {
        log.info("delete");
        return service.getAll();
    }

}