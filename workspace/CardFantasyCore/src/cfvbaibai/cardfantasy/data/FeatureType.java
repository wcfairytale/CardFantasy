package cfvbaibai.cardfantasy.data;

public enum FeatureType {
    Snipe("�ѻ�"),
    ChainLightening("��������"),
    Penetration("����"),
    HolyLight("ʥ��"),
    Block("��");
    
    private String displayName;
    FeatureType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
}