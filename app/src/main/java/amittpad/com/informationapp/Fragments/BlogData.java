package amittpad.com.informationapp.Fragments;

/**
 * Created by AA on 9/4/17.
 */
public class BlogData {
    int icon;
    String title,fileName;

    public BlogData(int icon, String fileName, String title) {
        this.icon = icon;
        this.title = title;
        this.fileName = fileName;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
    public String getFileName() {
        return fileName;
    }
}
