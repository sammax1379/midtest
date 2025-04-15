package com.mycompany.app;


public class GameInitializer {
    public static GameContext init() {
        Room forest = new Room("森林入口", "你站在茂密森林的邊緣。", new Monster("哥布林", 30, 8), true);
        Room village = new Room("村莊", "這是一個寧靜的小村莊，村民們正忙碌著。", null, false);
        Room temple = new Room("廢棄神殿", "破碎的石牆上布滿藤蔓，空氣中瀰漫著魔法的氣息。", new Monster("骷髏戰士", 50, 12), false);

        forest.setExit("north", temple);
        forest.setExit("west", village);  // 新增村莊的連接

        temple.setExit("south", forest);
        village.setExit("east", forest);  // 反向連接

        Player player = new Player("勇者", 100, 15);

        System.out.println("[ 遊戲開始！歡迎 " + player.getName() + "！ ]");
        System.out.println("你目前在：" + forest.getName());
        Monster m = forest.getMonster();
        if (m != null && m.isAlive()) {
            System.out.println("你看到：" + m.getName() + "（HP: " + m.getHp() + "）");
        }
        System.out.println("可用方向：" + forest.getExitString());
        if (forest.hasPotion()) {
            System.out.println("有一瓶發光的治療藥水躺在地上。");
        }
        return new GameContext(player, forest);
    }
}
