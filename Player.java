package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int hp;
    private int attack;
    private int killCount = 0;
    private int totalDamage = 0;
    private List<Skill> skills = new ArrayList<>();  // 新增技能列表

    // constructor
    public Player(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    // 添加技能
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getSkills() {
        return skills;
    }

    // 其他方法保持不變
    public boolean isAlive() { return hp > 0; }
    public void takeDamage(int dmg) { hp -= dmg; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public void addKill() { killCount++; }
    public void addDamage(int dmg) { totalDamage += dmg; }
    public String getName() { return name; }
    public void heal(int amount) { this.hp = Math.min(100, this.hp + amount); }
    public int getTotalDamage() { return totalDamage; }
    public int getKillCount() { return killCount; }
}
