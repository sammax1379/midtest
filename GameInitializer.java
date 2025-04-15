package com.mycompany.app;

import java.util.Scanner;

public class GameInitializer {
    public static GameContext init() {
        Scanner scanner = new Scanner(System.in);

        // 讓玩家設置角色名稱、HP 和攻擊力
        System.out.print("請輸入勇者的名字：");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName, 100, 15);

        // 玩家可以自定義技能
        System.out.print("請輸入自定義技能的名稱：");
        String skillName = scanner.nextLine();
        System.out.print("請輸入技能的傷害值：");
        int skillDamage = scanner.nextInt();

        // 創建技能並讓玩家擁有這個技能
        Skill skill = new Skill(skillName, skillDamage);
        player.addSkill(skill);

        // 顯示玩家的技能
        System.out.println("你的技能已設置為：" + skill.getName() + "（傷害：" + skill.getDamage() + "）");

        // 創建房間
        Room forest = new Room("森林入口", "你站在茂密森林的邊緣。", new Monster("哥布林", 30, 8), true);
        Room temple = new Room("廢棄神殿", "破碎的石牆上布滿藤蔓，空氣中瀰漫著魔法的氣息。", new Monster("骷髏戰士", 50, 12), false);
        forest.setExit("north", temple);
        temple.setExit("south", forest);

        // 打印遊戲開始提示
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
