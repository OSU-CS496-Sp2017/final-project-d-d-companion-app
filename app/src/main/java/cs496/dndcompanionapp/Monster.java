package cs496.dndcompanionapp;

import java.util.Random;

/**
 * Created by Sanlador on 6/15/2017.
 */

public class Monster
{
    public String name;
    public int CR, AC, HP, Will, Fort, Ref, Atk, DmgBonus, DmgDice, XP, numDice;
    Random rand = new Random();

    private int setAC(int CR)
    {
        int ACTable[] = {14, 14, 14, 14, 14, 15, 16, 16, 16, 17, 17, 18, 18, 18, 19, 19, 19, 19, 20, 20};
        int ACtemp = ACTable[CR - 1];
        int n = rand.nextInt(40) - 20;
        return ACtemp + (ACtemp * n / 100);
    }

    private int setHP(int CR)
    {
        int HPTable[] = {30, 31, 32, 34, 45, 56, 68, 70, 73, 76, 83, 91, 99, 107, 114, 123, 133, 142, 152, 190};
        int HPtemp = HPTable[CR - 1];
        int n = rand.nextInt(40) - 20;
        return HPtemp + (HPtemp * n / 100);
    }

    private int setSave(int CR)
    {
        int SaveTable[] = {3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7};
        int Savetemp = SaveTable[CR - 1];
        int n = rand.nextInt(100) - 50;
        return Savetemp + (Savetemp * n / 100);
    }

    private int setAtk(int CR)
    {
        int AtkTable[] = {3, 3, 3, 3, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 10, 10, 10};
        int Atktemp = AtkTable[CR - 1];
        int n = rand.nextInt(40) - 20;
        return Atktemp + (Atktemp * n / 100);
    }

    private int setXP(int CR)
    {
        int XPTable[] = {200, 450, 700, 1100, 1800, 2300, 2900, 3900, 5000, 5900, 7200, 8400, 10000, 11500, 13000, 15000, 18000, 20000, 22000, 25000};
        return XPTable[CR - 1];
    }

    private int setDice(int CR)
    {
        int diceTable[] = {4, 6, 8, 10, 12};
        return diceTable[rand.nextInt(5)];
    }

    private int setDiceNum(int CR, int Dice)
    {
        int DmgTable[] = {3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 28, 30, 32, 34, 41, 43, 46, 48, 56};
        int totalDmg = DmgTable[CR - 1];
        int diceDmg = Dice / 2 + 1;
        if ((totalDmg / 2) / (Dice / 2 + 1) > 1)
            return (totalDmg / 2) / (Dice / 2 + 1);
        return 1;
    }

    private int setDmgBonus(int CR, int DmgDice, int NumDice)
    {
        int DmgTable[] = {3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 28, 30, 32, 34, 41, 43, 46, 48, 56};
        int totalDmg = DmgTable[CR - 1];
        int diceTotal = (DmgDice / 2 + 1) * NumDice;
        if (totalDmg - diceTotal > 0)
            return totalDmg - diceTotal;
        else
            return 1;
    }

    private String setName()
    {
        String[] Adjective = {"Thunder Iron", "Superfluous", "Pyromaniac", "Sparkly", "Wizened", "Tardy", "Legendary", "Amazing", "Fantastical", "Ware", "Sagely", "Shivvering", "Dire", "Sentient", "Leaping", "Miniature Giant", "Space"};
        String[] Name = {"Alligator", "Minotar", "Dragon", "Goblin", "Guardsman", "Guardswoman", "Kitten", "Elemental", "Horse", "Brigand", "Naga", "Serpent", "Dodo", "Wizard", "Snek", "Floof", "Hamster", "Space Hamster"};
        int AdjIndex = rand.nextInt(Adjective.length);
        int NameIndex = rand.nextInt(Name.length);
        return Adjective[AdjIndex] + " " + Name[NameIndex];
    }

    public Monster(int inputCR)
    {
        name = setName();
        AC = setAC(inputCR);
        HP = setHP(inputCR);
        Will = setSave(inputCR);
        Fort = setSave(inputCR);
        Ref = setSave(inputCR);
        Atk = setAtk(inputCR);
        DmgDice = setDice(inputCR);
        numDice = setDiceNum(inputCR, DmgDice);
        DmgBonus = setDmgBonus(inputCR, DmgDice, numDice);
        XP = setXP(inputCR);
        CR = inputCR;
    }

}
