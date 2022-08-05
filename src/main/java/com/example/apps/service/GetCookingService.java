package com.example.apps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.apps.DAO.CookingInfoDAO;
import com.example.apps.bean.Cooking;

@Repository
public class GetCookingService {
    
    @Autowired
    private CookingInfoDAO CookingDAO;

    public int getLatestDishId() {
        return CookingDAO.getLatestDishId();
    }

    // ランダムにレシピを取得して返すメソッド
    public List<Cooking> SelectRecipesList(int selective, int LatestDishId) {
        List<Integer> SelectiveList= new ArrayList<Integer>();//ランダムに生成したID収納用リスト
        Random random = new Random();
        for(int i=0;i<selective;i++){
            int randomValue = random.nextInt(LatestDishId)+1;
            if(!SelectiveList.contains(randomValue)){//被らなければリストに加える
                SelectiveList.add(randomValue);
            }else{
                i--;
            }
        }

        //IDからレシピを取得
        List<Cooking> SelectedRecipesList = new ArrayList<>();//Listにレシピをいれる
            for(int i=0;i<SelectiveList.size();i++){//レシピリストを作成
                SelectedRecipesList.add(this.getIngredients(SelectiveList.get(i)));
        }

        return SelectedRecipesList;
    }

    public Cooking getIngredients(int DishId) {
        return CookingDAO.getCookingInfo(DishId);
    }

    public String getDishName(int parseInt) {
        return CookingDAO.getDishName(parseInt);
    }

    public String getGenre(int parseInt) {
        return CookingDAO.getGenreName(parseInt);
    }

}
