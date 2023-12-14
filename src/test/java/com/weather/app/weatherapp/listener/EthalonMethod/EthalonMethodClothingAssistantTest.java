package com.weather.app.weatherapp.listener.EthalonMethod;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EthalonMethodClothingAssistantTest {

    private EthalonMethodClothingAssistant clothingAssistant;
    @BeforeEach
    void setUp() {
        clothingAssistant = new EthalonMethodClothingAssistant();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAdviceCold() {
        assertLinesMatch(Arrays.asList("https://media1.popsugar-assets.com/files/thumbor/WoMk1FxsWp3a8VYInOK_rblhujY/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2016/01/20/058/n/1922564/2476dbb596684bd8_GettyImages-505729648_master/i/Layer-up-your-knits-look-so-cosy-youll-feel-like-youre-still.jpg"), clothingAssistant.getAdvice(3, 95, 20));
    }

    @Test
    void getAdviceHot() {
        assertLinesMatch(Arrays.asList("https://regalgentleman.com/cdn/shop/articles/Summer_Oufits_1024x1024_1eb22abe-e47b-495b-9e23-3340c005fbfb.jpg?v=1536133088"), clothingAssistant.getAdvice(30, 95, 5));
    }
    @Test
    void getAdviceNormal() {
        assertLinesMatch(Arrays.asList("https://i.pinimg.com/1200x/78/80/35/78803531f52ec5ace918925f999e7ffc.jpg"), clothingAssistant.getAdvice(13, 80, 6));
    }
}