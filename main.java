package com.mycompany.app;



public class main {
    public static void main(String[] args) {
        GameContext context = GameInitializer.init();
        GameEngine engine = new GameEngine(context);
        engine.start();
    }
}
