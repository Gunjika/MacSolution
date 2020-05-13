package gunjika.varshney.gla.macsolution;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<uploadPdf> listData;
    public Adapter(List<uploadPdf> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        uploadPdf ld=listData.get(position);
        holder.txtname.setText(ld.getNamee());
        holder.txtmovie.setText(ld.getUrl());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname,txtmovie;
        public ViewHolder(View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.nametxt);
            txtmovie=itemView.findViewById(R.id.movietxt);
        }
    }
}
