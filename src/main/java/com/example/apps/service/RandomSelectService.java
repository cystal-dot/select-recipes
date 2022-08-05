package com.example.apps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apps.DAO.CookingInfoDAO;
import com.example.apps.bean.Cooking;

@Service
public class RandomSelectService {

    @Autowired
    CookingInfoDAO CookingDAO;

    // ランダムにレシピを取得して返すメソッド
    public List<Cooking> RandomSelectRecipes(int selective, int latestDishId) {
        List<Integer> SelectiveList= new ArrayList<Integer>();//ランダムに生成したID収納用リスト
        Random random = new Random();
        for(int i=0;i<selective;i++){
            int randomValue = random.nextInt(latestDishId)+1;
            if(!SelectiveList.contains(randomValue)){//被らなければリストに加える
                SelectiveList.add(randomValue);
            }else{
                i--;
            }
        }

        //IDからレシピを取得
        List<Cooking> SelectedRecipesList = new ArrayList<>();//Listにレシピをいれる
            for(int i=0;i<SelectiveList.size();i++){//レシピリストを作成
                SelectedRecipesList.add(CookingDAO.getCookingInfo(SelectiveList.get(i)));
        }

        return SelectedRecipesList;
    }
}
