package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final String MEALS = "/meals.jsp";
    private static final String CREATE_MEAL = "/createMeal.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("forward to meals");
        request.setCharacterEncoding("UTF-8");
        String forward = "";
        String action = request.getParameter("action");
        switch (action == null ? "info" : action) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("mealId"));
                MealsUtil.delete(id);
                request.setAttribute("meals", MealsUtil.getTos(MealsUtil.meals, MealsUtil.DEFAULT_CALORIES_PER_DAY));
                forward = MEALS;
                break;
            case "update":
                id = Integer.parseInt(request.getParameter("mealId"));
                request.setAttribute("meal", MealsUtil.getMeal(id));

                forward = CREATE_MEAL;
                break;
            case "create":
                forward = CREATE_MEAL;
                break;
            default:
                request.setAttribute("meals", MealsUtil.getTos(MealsUtil.meals, MealsUtil.DEFAULT_CALORIES_PER_DAY));
                forward = MEALS;
        }

//        request.setAttribute("meals", MealsUtil.getTos(MealsUtil.meals, MealsUtil.DEFAULT_CALORIES_PER_DAY));
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        meal.setDescription(req.getParameter("description"));
        meal.setDateTime(LocalDateTime.parse(req.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        String mealId = req.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            if (MealsUtil.meals.size() != 0) {
                int newId = MealsUtil.meals.get(MealsUtil.meals.size() - 1).getId() + 1;
                meal.setId(newId);
                MealsUtil.addNewMeal(meal);
            } else {
                meal.setId(1);
                MealsUtil.addNewMeal(meal);
            }

        } else  {
            meal.setId(Integer.valueOf(mealId));
            MealsUtil.updateMeal(meal);
        }
        req.setAttribute("meals", MealsUtil.getTos(MealsUtil.meals, MealsUtil.DEFAULT_CALORIES_PER_DAY));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
