package springBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springBootApp.entities.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDao;

    @RequestMapping(value="/categories/viewAllCategories")
    public String viewAllCategories(ModelMap model) {
        model.addAttribute("categories",categoryDao.findAll());
        return "categories/viewAllCategories";
    }

    @RequestMapping(value="/categories/viewCategory/{categoryId}")
    public String viewCategory(@PathVariable("categoryId") Long categoryId, ModelMap model) {
        Category category = categoryDao.findOne(categoryId);
        model.addAttribute("category",category);
        return "categories/viewCategory";
    }

    @RequestMapping(value="/categories/addCategory")
    public String addCategory(ModelMap model) {
        Category category = new Category();
        model.addAttribute("category",category);
        return "categories/addCategory";
    }


    @RequestMapping(value="/categories/saveNewCategory")
    public String saveNewCategory(@ModelAttribute("category") Category category) {
        if(category != null) {
            categoryDao.save(category);
        } else {
            System.out.println("ERROR: did NOT save new category, category was NULL!");
        }
        return "redirect:/categories/viewAllCategories";
    }
}
