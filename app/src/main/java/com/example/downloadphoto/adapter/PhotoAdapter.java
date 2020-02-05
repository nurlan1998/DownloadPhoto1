package com.example.downloadphoto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.downloadphoto.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private ArrayList<Photo> photos;
    private OnItemClickListener listener;

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }



    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.photo,
                parent, false);

        return new PhotoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int position) {

        Photo currentPhoto = photos.get(position);

        String title =currentPhoto.getUser();
        String photoUrl = currentPhoto.getPhotoUrl();

        photoViewHolder.titleTextView.setText(title);
        Picasso.get().load(photoUrl).fit().centerInside()
                .into(photoViewHolder.photoImageView);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView photoImageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
