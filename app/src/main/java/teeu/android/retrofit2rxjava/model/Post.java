package teeu.android.retrofit2rxjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private int number;
    private String title;
    private Banner banner;
}
