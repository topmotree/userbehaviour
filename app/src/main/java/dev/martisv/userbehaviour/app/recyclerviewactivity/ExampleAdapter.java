package dev.martisv.userbehaviour.app.recyclerviewactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import dev.martisv.userbehaviour.app.R;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private List<ExampleItem> exampleList;

    public ExampleAdapter(List<ExampleItem> exampleList) {
        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false);
        return new ExampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = exampleList.get(position);
        holder.textViewNumber.setText(currentItem.getNumber());
        holder.textViewTitle.setText(currentItem.getTitle());
        holder.textViewSubtitle.setText(currentItem.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber;
        TextView textViewTitle;
        TextView textViewSubtitle;

        ExampleViewHolder(View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.text_view_number);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewSubtitle = itemView.findViewById(R.id.text_view_subtitle);
        }
    }
}