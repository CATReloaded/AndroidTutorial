package cat.cattutorial.observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cat.cattutorial.R;

public class ProductsActivity extends AppCompatActivity {
    private User user = User.newInstance("Mohamed",
            "asdsa@asd.com",
            "01020202032030",
            new ArrayList<Product>());

    private Observer observer = new Observer() {
        @Override
        public void onNext(List<Product> product) {
            Log.d("First Activity", "products: " + product);
        }
    };

    private androidx.lifecycle.Observer<List<Product>> o = new androidx.lifecycle.Observer<List<Product>>() {
        @Override
        public void onChanged(List<Product> s) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        user.getFavoritesObservable().observe(observer);

        Log.d(this.getClass().getSimpleName(), "user name :" + user.name);
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        list.add(new Product(new Random().nextInt() + "", "asd", "asdas"));
        RecyclerView rv = findViewById(R.id.rv);
        ProductsAdapter adapter = new ProductsAdapter(new ProductsAdapter.ClickListener() {
            @Override
            public void onClick(Product product) {
                ProductDetailsActivity.open(ProductsActivity.this, product);
            }
        });
        adapter.submitList(list);
        rv.setAdapter(adapter);
    }
}
