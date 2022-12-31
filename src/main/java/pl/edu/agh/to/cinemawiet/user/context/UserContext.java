package pl.edu.agh.to.cinemawiet.user.context;


import pl.edu.agh.to.cinemawiet.user.model.User;


public final class UserContext {

    private final User user;

    private static UserContext instance;


    public UserContext(User user) {
        this.user = user;
    }

    public static UserContext getInstance() {
        return instance;
    }

    public static void login(User user) {
        instance = new UserContext(user);
    }

    public static void logout() {
       instance = new UserContext(null);
    }

    public User getUser() {
        return user;
    }
}