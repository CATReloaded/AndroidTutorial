package cat.cattutorial.observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cat.cattutorial.R;

public class ProductDetailsActivity extends AppCompatActivity {
    private User user = User.newInstance("Ahmed",
            "cccc@ccc.com",
            "qweqwd",
            new ArrayList<Product>());

    private Observer observer = new Observer() {
        @Override
        public void onNext(List<Product> product) {
            Log.d("Second Activity", "products: " + product);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        user.getFavoritesObservable().observe(observer);


        final Product product = (Product) getIntent().getSerializableExtra("product");
        ImageView img = findViewById(R.id.imgFav);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.favorites.add(product);
                user.setFavorites(new ArrayList<>(user.favorites));
                Log.d(ProductDetailsActivity.this.getClass().getSimpleName(), "user name :" + user.name);
            }
        });
    }

    public static void open(Activity activity, Product product) {
        Intent intent = new Intent(activity, ProductDetailsActivity.class);
        intent.putExtra("product", product);
        activity.startActivity(intent);
    }
}
