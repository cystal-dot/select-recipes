package com.example.selectrecipes.Controller;
import com.example.apps.controller.DefaultController;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.ui.Model;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class  DefaultControllerTest{

    @InjectMocks
    private DefaultController target;

    // @Mock
    // private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 戻り値テスト() {

        // when(target.age(any(), any())).thenReturn("age");
        // when(target.hello(any(),any(), any()));

        DefaultController targetnotmock = new DefaultController();

        String actualname = targetnotmock.view();
        // String actualage = target.age("a", model);
        // String actualhello = target.hello("name", "age", model);

        assertThat("view", is(actualname));
        // assertThat("age", is(actualage));
        // assertThat("hello", is(actualhello));
    }
}
