public class ProductDB {

    private static ProductDB instance = null;

    //Singleton design pattern used to ensure only one product db instance
    public ProductDB getInstance() {
        if(instance == null) {
            instance = new ProductDB();
        }
        return instance;
    }
    
}
