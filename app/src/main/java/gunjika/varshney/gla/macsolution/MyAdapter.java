package gunjika.varshney.gla.macsolution;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Profile> profiles;
    public MyAdapter(Context c,ArrayList<Profile> p)
    {
        context=c;
        profiles=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.itemView.setBackgroundColor(currentColor);
//        holder.parent.setBackgroundColor(currentColor);
        holder.name.setText(profiles.get(position).getName());
        holder.email.setText(profiles.get(position).getEmail());
        holder.address.setText(profiles.get(position).getAddress());
        holder.contact.setText(profiles.get(position).getContact());
        holder.eduaction.setText(profiles.get(position).getEducation());
        holder.post.setText(profiles.get(position).getPost());
        holder.state.setText(profiles.get(position).getState());
        holder.district.setText(profiles.get(position).getDistrict());
        holder.tehsil.setText(profiles.get(position).getTehsil());
        holder.pin.setText(profiles.get(position).getPin());
        holder.btn.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,email,address,contact,eduaction,post,state,district,tehsil,pin;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            address=itemView.findViewById(R.id.address);
            contact=itemView.findViewById(R.id.contact);
            eduaction=itemView.findViewById(R.id.education);
            post=itemView.findViewById(R.id.post);
            state=itemView.findViewById(R.id.state);
            district=itemView.findViewById(R.id.district);
            tehsil=itemView.findViewById(R.id.tehsil);
            pin=itemView.findViewById(R.id.pin);
            btn = itemView.findViewById(R.id.checkDetails);

        }
        public void onClick(final int position)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+"is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
