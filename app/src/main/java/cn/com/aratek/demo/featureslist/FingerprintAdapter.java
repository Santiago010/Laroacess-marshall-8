package cn.com.aratek.demo.featureslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import cn.com.aratek.demo.R;
import cn.com.aratek.demo.featuresrequest.ResListEmployee2;


public class FingerprintAdapter extends RecyclerView.Adapter<FingerprintAdapter.ViewHolder> {


    public List<ResListEmployee2> data;
    public View.OnClickListener clickListenerFP;




    public FingerprintAdapter(List<ResListEmployee2> data, View.OnClickListener clickListenerFP) {
        this.data = data;
        this.clickListenerFP = clickListenerFP;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,clickListenerFP);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResListEmployee2 s = data.get(position);
        holder.textId.setText(String.valueOf(s.getDni()) );
        holder.textUsername.setText(s.getFirst_name());
        holder.textLastName.setText(s.getFirst_lastname());
        holder.icon_fp.setTag(s);

    }

    @Override
    public int getItemCount() {
       return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textUsername;
        public TextView textId;

        public TextView textLastName;
        public TextView icon_fp;

        public ViewHolder(View itemView,View.OnClickListener clickListenerFP) {
            super(itemView);
            textId = itemView.findViewById(R.id.idFP);
            textUsername = itemView.findViewById(R.id.nameFP);
            textLastName = itemView.findViewById(R.id.lastNameFP);
            icon_fp = itemView.findViewById(R.id.icon_fp);
            icon_fp.setOnClickListener(clickListenerFP);

        }

    }

}
