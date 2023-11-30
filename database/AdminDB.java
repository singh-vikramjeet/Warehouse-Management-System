public class AdminDB {

    private static AdminDB instance = null;

    //Singleton design pattern used to ensure only one admin db instance
    public AdminDB getInstance() {
        if(instance == null) {
            instance = new AdminDB();
        }
        return instance;
    }
    
}
