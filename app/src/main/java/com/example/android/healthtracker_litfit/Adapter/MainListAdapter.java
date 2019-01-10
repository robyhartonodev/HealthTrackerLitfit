package com.example.android.healthtracker_litfit.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.healthtracker_litfit.Model.MainItem;
import com.example.android.healthtracker_litfit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder> {

    // Private variables for constructors
    private ArrayList<MainItem> mainItemList;
    private Context mContext;

    // Constructor
    public MainListAdapter(Context context, ArrayList<MainItem> mainItemList) {
        this.mainItemList = mainItemList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_list_item, parent, false);
        return new MainListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder mainListViewHolder, int position) {
        // Set View in the CardView
        switch (position) {
            // Weight
            case 0:
                // Set Image
                Picasso.get().load(R.drawable.ic_weight).into(mainListViewHolder.ivMainItemImage);
                // Set Title
                mainListViewHolder.txtMainItemTitle.setText(mainItemList.get(0).getTitle());
                // Set Info
                mainListViewHolder.txtMainItemInfo.setText(mainItemList.get(0).getInfo());
                // Set CardView Background
                mainListViewHolder.cvMainItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.red100));
                break;
            // Calorie
            case 1:
                // Set Image
                Picasso.get().load(R.drawable.ic_calorie_burn).into(mainListViewHolder.ivMainItemImage);
                // Set Title
                mainListViewHolder.txtMainItemTitle.setText(mainItemList.get(1).getTitle());
                // Set Info
                mainListViewHolder.txtMainItemInfo.setText(mainItemList.get(1).getInfo());
                // Set CardView Background
                mainListViewHolder.cvMainItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.red200));
                break;
            case 2:
                // Set Image
                Picasso.get().load(R.drawable.ic_food).into(mainListViewHolder.ivMainItemImage);
                // Set Title
                mainListViewHolder.txtMainItemTitle.setText(mainItemList.get(2).getTitle());
                // Set Info
                mainListViewHolder.txtMainItemInfo.setText(mainItemList.get(2).getInfo());
                // Set CardView Background
                mainListViewHolder.cvMainItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.red300));
                break;
            case 3:
                // Set Image
                Picasso.get().load(R.drawable.ic_water).into(mainListViewHolder.ivMainItemImage);
                // Set Title
                mainListViewHolder.txtMainItemTitle.setText(mainItemList.get(3).getTitle());
                // Set Info
                mainListViewHolder.txtMainItemInfo.setText(mainItemList.get(3).getInfo());
                // Set CardView Background
                mainListViewHolder.cvMainItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.red400));
                break;
            case 4:
                // Set Image
                Picasso.get().load(R.drawable.ic_bed).into(mainListViewHolder.ivMainItemImage);
                // Set Title
                mainListViewHolder.txtMainItemTitle.setText(mainItemList.get(4).getTitle());
                // Set Info
                mainListViewHolder.txtMainItemInfo.setText(mainItemList.get(4).getInfo());
                // Set CardView Background
                mainListViewHolder.cvMainItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.red500));
                break;
            case 5:
                // Set Image
                Picasso.get().load(R.drawable.ic_heartbeat).into(mainListViewHolder.ivMainItemImage);
                // Set Title
                mainListViewHolder.txtMainItemTitle.setText(mainItemList.get(5).getTitle());
                // Set Info
                mainListViewHolder.txtMainItemInfo.setText(mainItemList.get(5).getInfo());
                // Set CardView Background
                mainListViewHolder.cvMainItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.red600));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mainItemList.size();
    }

    public class MainListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtMainItemTitle;
        TextView txtMainItemInfo;
        ImageView ivMainItemImage;
        CardView cvMainItem;

        public MainListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMainItemTitle = itemView.findViewById(R.id.textview_main);
            txtMainItemInfo = itemView.findViewById(R.id.textview_main_info);
            ivMainItemImage = itemView.findViewById(R.id.imageview_main);
            cvMainItem = itemView.findViewById(R.id.cardview_mainitem);

            // Set the OnClickListener to entire view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get position
            int pos = getAdapterPosition();

            switch (pos) {
                case 0:
                    // Debug Toast
                    Toast.makeText(mContext, pos + "clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    // Debug Toast
                    Toast.makeText(mContext, pos + "clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    // Debug Toast
                    Toast.makeText(mContext, pos + "clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    // Debug Toast
                    Toast.makeText(mContext, pos + "clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    // Debug Toast
                    Toast.makeText(mContext, pos + "clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    // Debug Toast
                    Toast.makeText(mContext, pos + "clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
