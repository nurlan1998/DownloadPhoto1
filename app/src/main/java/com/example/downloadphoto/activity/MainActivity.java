package com.example.downloadphoto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.downloadphoto.adapter.Photo;
import com.example.downloadphoto.adapter.PhotoAdapter;
import com.example.downloadphoto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.downloadphoto.utils.Util.KEY_DOWNLOADS;
import static com.example.downloadphoto.utils.Util.KEY_IMAGE_HEIGHT;
import static com.example.downloadphoto.utils.Util.KEY_IMAGE_WIDTH;
import static com.example.downloadphoto.utils.Util.KEY_TYPE;
import static com.example.downloadphoto.utils.Util.KEY_USER;
import static com.example.downloadphoto.utils.Util.KEY_VIEWS;

public class MainActivity extends AppCompatActivity implements PhotoAdapter.OnItemClickListener {


    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private ArrayList<Photo> photos;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));

        photos = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        photoAdapter = new PhotoAdapter(this, photos);

        getPhoto();

    }

    private void getPhoto() {
        String url = "https://pixabay.com/api/?key=14224389-1037257e6786d86ebd031ee76&q=yellow+flowers&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String user = jsonObject.getString("user");
                        String userImageURL = jsonObject.getString("userImageURL");
                        String type = jsonObject.getString("type");
                        String tags = jsonObject.getString("tags");
                        String imageWidth = jsonObject.getString("imageWidth");
                        String imageHeight = jsonObject.getString("imageHeight");
                        String downloads = jsonObject.getString("downloads");
                        String views = jsonObject.getString("views");


                        Photo photo = new Photo();
                        photo.setUser(user);
                        photo.setPhotoUrl(userImageURL);
                        photo.setType(type);
                        photo.setImageHeight(imageHeight);
                        photo.setImageWidth(imageWidth);
                        photo.setDownloads(downloads);
                        photo.setViews(views);

                        photos.add(photo);

                        photoAdapter = new PhotoAdapter(MainActivity.this, photos);

                        photoAdapter.setOnItemClickListener(MainActivity.this);

                        recyclerView.setAdapter(photoAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    public void onItemClick(int position){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        Photo clickedPhoto = photos.get(position);

        intent.putExtra(KEY_USER,clickedPhoto.getUser());
        intent.putExtra(KEY_TYPE,clickedPhoto.getType());
        intent.putExtra(KEY_IMAGE_HEIGHT,clickedPhoto.getImageHeight());
        intent.putExtra(KEY_IMAGE_WIDTH,clickedPhoto.getImageWidth());
        intent.putExtra(KEY_DOWNLOADS,clickedPhoto.getDownloads());
        intent.putExtra(KEY_VIEWS,clickedPhoto.getViews());

        startActivity(intent);
    }
}
