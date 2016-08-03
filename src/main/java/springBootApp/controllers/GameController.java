package springBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBootApp.entities.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GameController {

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value="/games/viewAllGames")
    public String viewAllGames(ModelMap model) {
        model.addAttribute("games",gameDAO.findAll());
        return "games/viewAllGames";
    }

    @RequestMapping(value="/games/findGame")
    public String findGame() {
        return "games/findGame";
    }

    @RequestMapping(value="/games/viewGame/{gameId}")
    public String viewGame(@PathVariable("gameId") Long gameId, ModelMap model) {
        Game game = gameDAO.findOne(gameId);
        Category category = categoryDAO.findOne(game.getCategoryId());
        model.addAttribute("category",category);
        model.addAttribute("game",game);
        return "games/viewGame";
    }

    @RequestMapping(value="/games/addGame")
    public String addGame(ModelMap model) {
        Game game = new Game();
        model.addAttribute("game",game);
        model.addAttribute("categoryIdAndNameList", getCategoryIdsAndNames());
        model.addAttribute("vendorIdAndNameList", getVendorIdsAndNames());
        return "games/addGame";
    }


    @RequestMapping(value="/games/saveNewGame")
    public String saveNewGame(@ModelAttribute("game") Game game) {
        if(game != null) {
            gameDAO.save(game);
        } else {
            System.out.println("ERROR: did NOT save new game, game was NULL!");
        }
        return "redirect:/games/viewAllGames";
    }

    private Map<String, String> getCategoryIdsAndNames() {
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("-1","-- None --");
        for(Category category : categoryDAO.findAll()) {
            hashMap.put(category.getCategoryId()+"",category.getCategoryTitle());
        }
        return hashMap;
    }

    private Map<String, String> getVendorIdsAndNames() {
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("-1","-- None --");
        // TODO: loop through vendors here...
        return hashMap;
    }
}
