package com.example.idilika.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.idilika.model.Dish;
import com.example.idilika.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.DishViewHolder>{
        private ArrayList<Dish> dishes;
        Context context;
        SharedPreferences sharedPreferences;
        final static String SAVED_CHECKBOX = "saved_checkbox";
        final static String SHARED_PREFS = "shared_prefs";

public Adapter( Context context, ArrayList<Dish> dishes){
        this.context=context;
        this.dishes=dishes;}

        @NonNull
        @Override
        public Adapter.DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent,
                false);
                return new DishViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.DishViewHolder holder, int position) {
                Dish dish = dishes.get(position);
                holder.name.setText(dish.getName());
                holder.price.setText(dish.getPrice().toString());
                holder.description.setText(dish.getDescription());
                holder.calories.setText(dish.getNutritionFacts().getCalories().toString());
                holder.weigh.setText(dish.getNutritionFacts().getWeight().toString());
                holder.protein.setText(dish.getNutritionFacts().getProtein().toString());
                holder.fat.setText(dish.getNutritionFacts().getFat().toString());
                holder.carbohydrates.setText(dish.getNutritionFacts().getCarbohydrates().toString());
                Glide.with(context).load(dish.getImage()).apply(RequestOptions.centerCropTransform())
                        .into(holder.image);
                if(dish.getId()==loadFav(SAVED_CHECKBOX)) {
                        holder.checkBox.setChecked(dish.getId()==loadFav(SAVED_CHECKBOX));
                }
                else holder.checkBox.setChecked(dish.getId()==loadFav(SAVED_CHECKBOX));
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (holder.checkBox.isChecked()) {
                                        holder.checkBox.setChecked(true);
                                        sharedPreferences = context.getSharedPreferences("favorite",
                                                Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putInt(SAVED_CHECKBOX,holder.checkBox.getId()).apply();
                                        notifyDataSetChanged();

                                } else {
                                        holder.checkBox.setChecked(false);
                                        removeFav(SAVED_CHECKBOX);
                                }
                        }
                });
                loadFav(SAVED_CHECKBOX);
        }

        public void removeFav(String SAVED_CHECKBOX){
                sharedPreferences = context.getSharedPreferences("favorite",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(SAVED_CHECKBOX).apply();
        }

        public int loadFav(String SAVED_CHECKBOX ){
                sharedPreferences = context.getSharedPreferences("favorite",
                        Context.MODE_PRIVATE);
                return sharedPreferences.getInt(SAVED_CHECKBOX,0);
        }


        @Override
        public int getItemCount() {
                return dishes.size();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void setDishes(ArrayList<Dish> dishes) {
        this.dishes=dishes;
        notifyDataSetChanged();
        }

        public static class DishViewHolder extends RecyclerView.ViewHolder{
                TextView name, price, description;
        TextView weigh, fat,protein, calories, carbohydrates;
        ImageView image;
        CheckBox checkBox;

                public DishViewHolder(@NonNull View itemView) {
                        super(itemView);
                        name = itemView.findViewById(R.id.name);
                        price = itemView.findViewById(R.id.price);
                        description = itemView.findViewById(R.id.description);
                        image = itemView.findViewById(R.id.image);
                        weigh = itemView.findViewById(R.id.weigh);
                        fat = itemView.findViewById(R.id.fat);
                        protein = itemView.findViewById(R.id.protein);
                        calories = itemView.findViewById(R.id.calories);
                        carbohydrates = itemView.findViewById(R.id.carbohydrates);
                        checkBox = itemView.findViewById(R.id.checkbox);
                }

        }
}
