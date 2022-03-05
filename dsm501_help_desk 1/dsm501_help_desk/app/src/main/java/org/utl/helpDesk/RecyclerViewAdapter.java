package org.utl.helpDesk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.utl.helpDesk.databinding.TicketBinding;
import org.utl.helpDesk.model.Ticket;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private final ArrayList<Ticket> tickets;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(ArrayList<Ticket> tickets, Context context, OnItemClickListener onItemClickListener) {
        this.tickets = tickets;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(TicketBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ticket currentTicket = tickets.get(position);
        int imageResId = context.getResources().getIdentifier(currentTicket.getImageResName(), "drawable", context.getPackageName());
        holder.binding.ticketImage.setImageResource(imageResId);
        holder.binding.txtId.setText(String.valueOf(currentTicket.getId()));
        holder.binding.txtDevice.setText(currentTicket.getDevice());
        holder.binding.txtDate.setText(currentTicket.getDate());
        holder.binding.txtTime.setText(currentTicket.getTimeOf());
        holder.binding.txtStatus.setText(currentTicket.getStringStatus());
        holder.binding.txtType.setText(currentTicket.getType());
        holder.binding.txtDescription.setText(currentTicket.getDescription());
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TicketBinding binding;

        public MyViewHolder(TicketBinding binding, OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
