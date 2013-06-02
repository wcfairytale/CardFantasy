package cfvbaibai.cardfantasy.data;

import java.util.List;


public class Card implements Cloneable {
    private CardData sourceInfo;
    private Adjustments adjustments;
    private int exp;
    
    public Card(CardData sourceInfo) {
        this.sourceInfo = sourceInfo;
        this.adjustments = new Adjustments();
        this.exp = 0;
    }
    
    public String getName() {
        return this.sourceInfo.getName();
    }
    
    public int getSummonSpeed() {
        return this.sourceInfo.getSummonSpeed();
    }
    
    public int getLevel() {
        return sourceInfo.getGrowth().getLevel(exp);
    }
    
    public int getInitAT() {
        return this.sourceInfo.getBaseAT() + this.sourceInfo.getIncrAT() * this.getLevel();
    }
    
    public int getMaxHP() {
        return this.sourceInfo.getBaseHP() + this.sourceInfo.getIncrHP() * this.getLevel();
    }

    public List<Feature> getAllFeatures() {
        return sourceInfo.getFeatures();
    }

    public void growToLevel(int level) {
        this.exp = sourceInfo.getGrowth().getRequiredExp(level);
    }
}