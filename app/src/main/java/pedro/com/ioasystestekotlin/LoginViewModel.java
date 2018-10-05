package pedro.com.ioasystestekotlin;

import android.arch.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private String userId;
//    private User user;

    public void init(String userId) {
        this.userId = userId;
    }
//    public User getUser() {
//        return user;
//    }
}
