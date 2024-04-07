package com.devpro.android54_day8;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android54_day8.adapters.ProductAdapter;
import com.devpro.android54_day8.api.ApiUtils;
import com.devpro.android54_day8.interfaces.IHomeView;
import com.devpro.android54_day8.objects.AllProductResponse;
import com.devpro.android54_day8.objects.Product;
import com.devpro.android54_day8.presenters.HomePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IHomeView, ProductAdapter.IOnProductItemClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView rvProduct;
    private HomePresenter mHomePresenter;
    private ProductAdapter mProductAdapter;
    private ArrayList<Product> mListProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        getAllProduct();
    }

    private void initData() {
        mHomePresenter = new HomePresenter(this);
        mListProduct = new ArrayList<>();
        mProductAdapter = new ProductAdapter(mListProduct);
        mProductAdapter.setCallback(this);
        rvProduct.setAdapter(mProductAdapter);
    }

    public void getAllProduct(){
        mHomePresenter.getAllProduct();
    }

    private void initView() {
        rvProduct = findViewById(R.id.rvProduct);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);
    }

    @Override
    public void getAllProductSuccess(AllProductResponse response) {
        mListProduct.clear();
        mProductAdapter.updateData((ArrayList<Product>) response.getProducts());
    }

    @Override
    public void getAllProductError(String error) {
        Log.d(TAG, "getAllProductError: "+error);
    }

    @Override
    public void onShopNowClick(Product product) {
        Toast.makeText(this, ""+product.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavorite(int position) {
        Product product = mListProduct.get(position);
        product.setFavorite(!product.isFavorite());

        mListProduct.set(position, product);
        mProductAdapter.notifyItemChanged(position);
    }
}