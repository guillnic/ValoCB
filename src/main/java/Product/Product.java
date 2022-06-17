import java.util.ArrayList;
import java.util.Objects;

public class Product {
    private String name;
    private ArrayList<Underlying> underlyings = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product : " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Product){
            Product product = (Product) o;
            return product.getName().equals(this.name);
       } else {
           return false;
       }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
