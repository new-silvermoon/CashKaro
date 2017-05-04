package com.silvermoon.storepage;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.silvermoon.cashkaro.R;
import com.silvermoon.data.StoreDeals;

/**
 * Adapater for StorePageActivity's recycler view
 */

public class StorePageAdapter extends RecyclerView.Adapter<StorePageAdapter.DealsHolder> {

    private static final String TAG = StorePageAdapter.class.getSimpleName();

    /* Callback for favorite,get Code and share button click events */
    public interface OnButtonClickListener {
        void onfavClick(boolean fav,long id,StoreDeals deals );

        void onShareClick(StoreDeals deals);

        void onGetCodeClick(StoreDeals deals);
    }

    //Constructor
    public StorePageAdapter(Cursor cursor){ mCursor = cursor;}


    //View holder for each view
    public class DealsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView couponTitle,couponDetails,additionalInfo;
        private ImageButton favButton,shareButton;
        private Button getCodeButton;
        public DealsHolder(View dealsView){
            super(dealsView);

            couponTitle = (TextView)dealsView.findViewById(R.id.couponTitle);
            couponDetails = (TextView)dealsView.findViewById(R.id.couponDetails);
            additionalInfo =(TextView)dealsView.findViewById(R.id.additionalInfo);
            favButton =(ImageButton)dealsView.findViewById(R.id.favButton);
            shareButton=(ImageButton)dealsView.findViewById(R.id.shareButton);
            getCodeButton=(Button)dealsView.findViewById(R.id.getCodeButton);

            favButton.setOnClickListener(this);
            shareButton.setOnClickListener(this);
            getCodeButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v==favButton){favouriteSelected(this);}
            else if(v==shareButton){shareDeal(this);}
            else{getCodeSelected(this);}
        }
    }

    private void shareDeal(DealsHolder dealsHolder) {
        StoreDeals deal = null;

        if(mButtonClickListener !=null){
            if(mCursor.moveToPosition(dealsHolder.getAdapterPosition())){deal = new StoreDeals(mCursor);}
            mButtonClickListener.onShareClick(deal);
        }
    }

    private void favouriteSelected(DealsHolder dealsHolder) {

        StoreDeals deal = null;

        if(mButtonClickListener != null){
            if(mCursor.moveToPosition(dealsHolder.getAdapterPosition())){deal = new StoreDeals(mCursor);}
            mButtonClickListener.onfavClick(deal.isFav,getItemId(dealsHolder.getAdapterPosition()),deal);
        }

    }
    private void getCodeSelected(DealsHolder dealsHolder){
        StoreDeals deals = null;

        if(mButtonClickListener !=null){
           if(mCursor.moveToPosition(dealsHolder.getAdapterPosition())){
               deals = new StoreDeals(mCursor);
           }
           else{
               deals = null;
           }
            mButtonClickListener.onGetCodeClick(deals);
        }
    }

    private Cursor mCursor;
    private OnButtonClickListener mButtonClickListener;



    public void setOnItemClickListener(OnButtonClickListener listener) {
        mButtonClickListener = listener;
    }



    @Override
    public DealsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_view_coupon_items,parent,false);

        return new DealsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DealsHolder holder, int position) {

        if(mCursor!=null){
            if(mCursor.moveToPosition(position)){

                StoreDeals deal = getItem(position);

                holder.couponTitle.setText(deal.dealHeading);
                holder.couponDetails.setText(deal.dealDesciption);
                holder.additionalInfo.setText(deal.additionalInfo);

                //Updating fav image as per the value present in Database
                if(deal.isFav){holder.favButton.setImageResource(R.drawable.ic_favorite_full);}
                else{holder.favButton.setImageResource(R.drawable.ic_favorite_empty);}
            }
        }
        else{
            Log.e (TAG, "onBindViewHolder: Cursor is null.");
        }

    }

    /**
     * Retrieve a {@link StoreDeals} for the data at the given position.
     *
     * @param position Adapter item position.
     *
     * @return A new {@link StoreDeals} filled with the position's attributes.
     */
    public StoreDeals getItem(int position) {
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Invalid item position requested");
        }

        return new StoreDeals(mCursor);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    public void swapCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }
}
