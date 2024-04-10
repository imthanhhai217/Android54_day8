package com.devpro.android54_day8;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android54_day8.adapters.ProductAdapter;
import com.devpro.android54_day8.interfaces.IHomeView;
import com.devpro.android54_day8.objects.dummy.AllProductResponse;
import com.devpro.android54_day8.objects.dummy.Product;
import com.devpro.android54_day8.objects.resful.Data;
import com.devpro.android54_day8.objects.resful.ProductRequest;
import com.devpro.android54_day8.objects.resful.ProductResponse;
import com.devpro.android54_day8.presenters.HomePresenter;

import java.util.ArrayList;
import java.util.Calendar;

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

    public void getAllProduct() {
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
        Log.d(TAG, "getAllProductError: " + error);
    }

    @Override
    public void updateProductSuccess(ProductResponse response) {
        Log.d(TAG, "updateProductSuccess: "+response.toString());
    }

    @Override
    public void updateProductError(String error) {
        Log.d(TAG, "updateProductError: "+error);
    }

    @Override
    public void onShopNowClick(Product product) {
        Toast.makeText(this, "" + product.getTitle(), Toast.LENGTH_SHORT).show();

        ProductRequest requestProduct = new ProductRequest();
        requestProduct.setName(product.getTitle());
        Data data = new Data();
        data.setYear(Calendar.getInstance().get(Calendar.YEAR));
        data.setCPUModel("Intel");
        data.setPrice(1900.0);
        data.setHardDiskSize("1 TB");
        requestProduct.setData(data);
        mHomePresenter.updateProduct(requestProduct);
    }

    @Override
    public void onFavorite(int position) {
        Product product = mListProduct.get(position);
        product.setFavorite(!product.isFavorite());

        mListProduct.set(position, product);
        mProductAdapter.notifyItemChanged(position);
    }
}