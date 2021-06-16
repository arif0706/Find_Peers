package com.example.ideas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RequestScreenAdapter extends RecyclerView.Adapter<RequestScreenAdapter.RequestCardHolder> {


    Context context;
    List<Requests_list> requests_list;
    RequestCardClickListener requestCardClickListener;

    public RequestScreenAdapter(Context context, List<Requests_list> requests_list,RequestCardClickListener requestCardClickListener){
        this.context=context;
        this.requests_list=requests_list;
        this.requestCardClickListener=requestCardClickListener;
    }
    @NonNull
    @NotNull
    @Override
    public RequestCardHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.request_card,parent,false);
        return new RequestCardHolder(view,requestCardClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestCardHolder holder, int position) {
        Requests_list list=requests_list.get(position);

        holder.textView.setText(list.getRequested_by().getFullname()+" has requested to join your group "+list.getGroup_id().getGroup_name());
    }

    @Override
    public int getItemCount() {
        return requests_list.size();
    }

    public class RequestCardHolder extends RecyclerView.ViewHolder{
        RequestCardClickListener requestCardClickListener;

        TextView textView;
        public RequestCardHolder(@NonNull @NotNull View itemView, RequestCardClickListener requestCardClickListener) {
            super(itemView);
            textView=itemView.findViewById(R.id.request_message);
            this.requestCardClickListener=requestCardClickListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestCardClickListener.onRequestClick(requests_list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface RequestCardClickListener{
        void onRequestClick(Requests_list requests_list);
    }
}
