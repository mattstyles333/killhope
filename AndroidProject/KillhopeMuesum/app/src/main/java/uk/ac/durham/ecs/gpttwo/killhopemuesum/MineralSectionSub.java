package uk.ac.durham.ecs.gpttwo.killhopemuesum;

/**
 * Created by Robert Clarke on 26-02-15.
 */
public class MineralSectionSub {

    private String title;
    private String info;

    public MineralSectionSub(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
