package com.example.praktikum_6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SpendAdapter(private val context: Context, private val data:ArrayList<SpendModel>): RecyclerView.Adapter<SpendAdapter.SpendViewHolder>() {

    class SpendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nama : TextView = itemView.findViewById(R.id.namaPengeluaran)
        var harga: TextView = itemView.findViewById(R.id.harga)
        val delButton: ImageButton = itemView.findViewById(R.id.deletebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendAdapter.SpendViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.content,parent,false)
        return SpendViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpendAdapter.SpendViewHolder, position: Int) {
        var spending:SpendModel = data.get(position)
        var curent = data[position]
        holder.nama.setText(spending.name)
        holder.harga.setText(spending.value)

        holder.delButton.setOnClickListener(){
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                data.removeAt(itemPosition)
                notifyItemRemoved(itemPosition)
            }
        }
        holder.itemView.setOnClickListener(){
            val intent: Intent = Intent(context,DetailSpend::class.java)
            var bundle: Bundle = Bundle()
            bundle.putString("judul", curent.name)
            bundle.putString("artis", curent.value)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }




}