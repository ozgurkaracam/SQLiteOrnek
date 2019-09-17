package com.example.sqliternek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Tutucu> {
    Context mContext;
    ArrayList<Kelimeler> kelimelers;
    Database database;
    KeliemelerDao kdao;

    public MyAdapter(Context mContext, ArrayList<Kelimeler> kelimelers) {
        this.mContext = mContext;
        this.kelimelers = kelimelers;
        this.database=new Database(mContext);
        this.kdao=new KeliemelerDao(this.database);
    }

    @NonNull
    @Override
    public Tutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.kelimeler_cardtasarim,parent,false);
        return new Tutucu(view);

    }

    @Override
    public void onBindViewHolder(final @NonNull Tutucu holder, final int position) {
               final Kelimeler kelime=kelimelers.get(position);
                holder.textView.setText(kelime.getId()+":"+kelime.getTurkce()+":"+kelime.getIngilizce());
                holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertBuilder=new AlertDialog.Builder(mContext);
                    alertBuilder.setTitle("Düzenle");
                    alertBuilder.setMessage("Metni Düzenleyin.");
                    alertBuilder.setCancelable(true);
                    LayoutInflater layoutInflater= (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    final View viewCustom=layoutInflater.inflate(R.layout.custom_alertdialog,null);
                    alertBuilder.setView(viewCustom);
                    final EditText etingilizce= (EditText) viewCustom.findViewById(R.id.editText2);
                    etingilizce.setText(kelime.getIngilizce());
                    final EditText etTurkce=(EditText) viewCustom.findViewById(R.id.editText3);
                    etTurkce.setText(kelime.getTurkce());

                    //TO DO SOMETHING...
                    alertBuilder.setNeutralButton("TAMAM!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            kdao.kelimeDuzenle(kelime.getId(),etTurkce.getText().toString(),etingilizce.getText().toString());
                            Toast.makeText(mContext,"Düzenleme Başarılı",Toast.LENGTH_LONG);

                        }
                    });
                    alertBuilder.show();
                    notifyDataSetChanged();

                }
            });
            holder.buttonSil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        kdao.kelimeSil(kelime.getId());
                        notifyItemRemoved(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return kelimelers.size();
    }

    public class Tutucu extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;
        Button buttonSil;

        public Tutucu(@NonNull View itemView) {
            super(itemView);
            this.buttonSil=itemView.findViewById(R.id.buttonSil);
            this.textView = itemView.findViewById(R.id.textView);
            this.button = itemView.findViewById(R.id.button);
        }
    }

}
