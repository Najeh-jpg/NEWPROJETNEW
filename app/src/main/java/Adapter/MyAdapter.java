package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recycleview.R;

import java.util.List;

import Model.Listitem;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List <Listitem>listitems;
    public MyAdapter(Context context, List listitem){
        this.context= context;
        this.listitems=listitem;


    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Listitem item = listitems.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.reduction.setText(item.getReduction());
        holder.image.setImageResource(item.getImage());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView description;
        private TextView reduction;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.title1);
            description=itemView.findViewById(R.id.desc1);
            reduction=itemView.findViewById(R.id.reduction1);
            image=itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            Listitem item =listitems.get(position);
            Toast.makeText(context ,item.getName(),Toast.LENGTH_SHORT).show();

        }
    }
}
