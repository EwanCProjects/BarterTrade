package currentUserProperties;

public class CurrentUser {
    private static CurrentUser currUser = null;

    public String currUserString;

    protected CurrentUser(){}

    public static synchronized CurrentUser getInstance() {
        if (null == currUser) {
            currUser = new CurrentUser();
        }
        return currUser;
    }
}
