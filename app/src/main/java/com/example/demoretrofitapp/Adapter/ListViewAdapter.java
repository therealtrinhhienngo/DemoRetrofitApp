package com.example.demoretrofitapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoretrofitapp.MainActivity;
import com.example.demoretrofitapp.ModalFunction.PersonRetrofitFunction;
import com.example.demoretrofitapp.Model.Person;
import com.example.demoretrofitapp.R;

import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Person> listPersons;

    public ListViewAdapter(Context context, ArrayList<Person> listPersons) {
        this.context = context;
        this.listPersons = listPersons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person currentObj = listPersons.get(position);
        PersonRetrofitFunction retrofitFunction = new PersonRetrofitFunction();

        holder.id.setText("Id: " + currentObj.getId());
        holder.name.setText("Name: " + currentObj.getName());
        holder.role.setText("Role: " + currentObj.getRole());

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivity = new Intent(context, MainActivity.class);
                switchActivity.putExtra("valueObj", currentObj);
                switchActivity.putExtra("mode", "update");
                context.startActivity(switchActivity);

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    listPersons.remove(currentObj);
                    holder.id.setText("");
                    retrofitFunction.deletePersonFunction(currentObj.getId(), context);
                }
                catch (Exception e){
                    Log.d("Delete Error", "Delete Err: " + e.toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersons.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, name, role;
        Button updateButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.itemId);
            name = itemView.findViewById(R.id.itemName);
            role = itemView.findViewById(R.id.itemRole);

            updateButton = itemView.findViewById(R.id.updateButtonItem);
            deleteButton = itemView.findViewById(R.id.deleteButtonItem);
        }
    }
}
