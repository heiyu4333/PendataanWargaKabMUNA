package com.example.pendataan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KKAdapter extends RecyclerView.Adapter<KKAdapter.VH> {
    private List<KK> data;

    public KKAdapter(List<KK> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kk, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        KK kk = data.get(position);
        holder.tvNomorKK.setText(kk.getNomorKK());
        holder.tvAlamat.setText(kk.getAlamat());
        holder.tvWilayah.setText(kk.getKelurahan() + ", " + kk.getKecamatan());
        holder.tvKondisi.setText(kk.getKondisiRumah());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNomorKK;
        TextView tvAlamat;
        TextView tvWilayah;
        TextView tvKondisi;
        VH(@NonNull View itemView) {
            super(itemView);
            tvNomorKK = itemView.findViewById(R.id.tvNomorKK);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvWilayah = itemView.findViewById(R.id.tvWilayah);
            tvKondisi = itemView.findViewById(R.id.tvKondisi);
        }
    }
}
