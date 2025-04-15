package com.mycompany.app;
import java.util.Scanner;

public class GameEngine {
    private GameContext context;
    private Scanner scanner = new Scanner(System.in);

    public GameEngine(GameContext context) {
        this.context = context;
    }

    public void start() {
        while (context.getPlayer().isAlive()) {
            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("look")) {
                Room r = context.getCurrentRoom();
                System.out.println("【房間】：" + r.getName());
                System.out.println("【描述】：" + r.getDescription());
                Monster m = r.getMonster();
                if (m != null && m.isAlive()) {
                    System.out.println("【怪物】：" + m.getName() + "（HP: " + m.getHp() + "）");
                }
                if (r.hasPotion()) {
                    System.out.println("【道具】：治療藥水");
                }
            } else if (input.equals("attack")) {
                Player p = context.getPlayer();
                Room r = context.getCurrentRoom();
                Monster m = r.getMonster();
                if (m == null || !m.isAlive()) {
                    System.out.println("這裡沒有可以攻擊的怪物！");
                } else {
                    m.takeDamage(p.getAttack());
                    p.addDamage(p.getAttack());
                    System.out.println("你對 " + m.getName() + " 造成了 " + p.getAttack() + " 傷害！");
                    if (m.isAlive()) {
                        p.takeDamage(m.getAttack());
                        System.out.println(m.getName() + " 反擊你，造成 " + m.getAttack() + " 傷害！");
                    } else {
                        System.out.println(m.getName() + " 被擊倒！");
                        p.addKill();
                    }
                    showStatus(p, m);
                }
            } else if (input.startsWith("skill ")) {
                String skillName = input.substring(6).trim();
                if (skillName.equals("fireball")) {
                    Player p = context.getPlayer();
                    Room r = context.getCurrentRoom();
                    Monster m = r.getMonster();
                    if (m == null || !m.isAlive()) {
                        System.out.println("這裡沒有可以攻擊的怪物！");
                    } else {
                        System.out.println("你施放了【火球術】！");
                        m.takeDamage(40);
                        p.addDamage(40);
                        if (!m.isAlive()) {
                            System.out.println("對 " + m.getName() + " 造成 40 傷害！");
                            System.out.println(m.getName() + " 被你燒成灰燼了！");
                            p.addKill();
                        } else {
                            System.out.println("對 " + m.getName() + " 造成 40 傷害！");
                            System.out.println(m.getName() + " 還活著！");
                            p.takeDamage(m.getAttack());
                            System.out.println();
                            System.out.println(m.getName() + " 反擊你，造成 " + m.getAttack() + " 傷害！");
                        }
                        showStatus(p, m);
                    }
                } else {
                    System.out.println("未知技能！");
                }
            } else if (input.equals("use potion")) {
                Room r = context.getCurrentRoom();
                Player p = context.getPlayer();
                if (r.hasPotion()) {
                    r.removePotion();
                    p.heal(30);
                    System.out.println("你喝下治療藥水，回復 30 HP！");
                    System.out.println("你的 HP：" + p.getHp());
                } else {
                    System.out.println("這裡沒有藥水可以使用！");
                }
            } else if (input.startsWith("move ")) {
                String dir = input.substring(5).trim();
                Room next = context.getCurrentRoom().getExit(dir);
                if (next != null) {
                    context.setCurrentRoom(next);
                    System.out.println("你移動到了：" + next.getName());
                    Monster m = next.getMonster();
                    if (m != null && m.isAlive()) {
                        System.out.println("你看到：" + m.getName() + "（HP: " + m.getHp() + "）");
                    }
                    System.out.println("可用方向：" + next.getExitString());
                    if (next.hasPotion()) {
                        System.out.println("有一瓶發光的治療藥水躺在地上。");
                    }
                } else {
                    System.out.println("你無法往那個方向走！");
                }
            } else if (input.equals("exit")) {
                System.out.println("感謝遊玩！再會，勇者！");
                return;
            } else {
                System.out.println("無效的指令。");
            }
        }

        System.out.println("你已死亡，遊戲結束。");
    }

    private void showStatus(Player p, Monster m) {
        System.out.println("\n=== 狀態更新 ===");
        System.out.println("你的 HP：" + p.getHp());
        if (m != null) {
            System.out.println(m.getName() + " HP：" + Math.max(0, m.getHp()));
        }
        System.out.println("總傷害：" + p.getTotalDamage() + "，擊殺數：" + p.getKillCount());
    }
}
