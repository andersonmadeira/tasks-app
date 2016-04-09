package br.com.andersonmadeira.tasks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by programador on 08/04/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> data;
    private Context context;

    public MyAdapter(Context c, List<String> dataSet) {
        data = dataSet;
        context = c;
    }

    public void add(int position, String item) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = data.indexOf(item);
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        final String title = data.get(position);
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked: " + title, Toast.LENGTH_SHORT).show();
            }
        });
        holder.icon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(title);
                return true;
            }
        });
        holder.tvTitle.setText(title);
        holder.tvDetails.setText("Details of "+data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView tvTitle;
        public TextView tvDetails;


        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDetails = (TextView) itemView.findViewById(R.id.tvDetails);
        }
    }
}
