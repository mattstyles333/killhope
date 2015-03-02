package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import java.util.ArrayList;

/**
 * Created by Robert Clarke on 26-02-15.
 */
public class Mineral {
    private String name;
    private String formula;
    private ArrayList<MineralSection> mineralSection;

    public void Mineral() {
        mineralSection = new ArrayList<MineralSection>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void addMineralSection(MineralSection ms)
    {
        mineralSection.add(ms);
    }

}
