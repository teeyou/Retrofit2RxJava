package teeu.android.retrofit2rxjava.model;

public class Post {
    private int number;
    private String title;

    public Post(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
