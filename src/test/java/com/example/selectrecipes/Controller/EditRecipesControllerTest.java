package com.example.selectrecipes.Controller;

import com.example.apps.bean.Cooking;
import com.example.apps.controller.EditRecipesController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.List;


public class EditRecipesControllerTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllIngredients() {
        EditRecipesController target = new EditRecipesController();
        List<Cooking> allIngredients = target.getAllIngredients(1);
        // 引数が1のとき、正しいIDを持つ材料を返すこと
        assert(allIngredients.get(1).getDishId() == 1);
        assert(allIngredients.get(1).getDishname().equals("ラーメン"));
        assert(allIngredients.get(1).getGenre().equals("中華"));
        assert(allIngredients.get(1).getIngredient1().equals("麺"));
        assert(allIngredients.get(1).getIngredient2().equals("チャーシュー"));
        assert(allIngredients.get(1).getIngredient3().equals("卵"));
        assert(allIngredients.get(1).getIngredient4().equals("ほうれん草"));
    }

    // 引数が1のとき、正しいIDを持つ材料を返すこと
    @Test
    public void testGetAllIngredients_1() {
        EditRecipesController target = new EditRecipesController();
        List<Cooking> allIngredients = target.getAllIngredients(1);
        assert(allIngredients.get(0).getDishId() == 1);
    }

}
