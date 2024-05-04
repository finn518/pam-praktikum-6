package com.example.praktikum_6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val data: ArrayList<SpendModel> = ArrayList()
    private lateinit var rv: RecyclerView
    private lateinit var adapter: SpendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv = findViewById(R.id.rv)
        val addForm: LinearLayout = findViewById(R.id.addForm)
        val submit: Button = findViewById(R.id.submit)
        val etName: EditText = findViewById(R.id.etName)
        val etValue: EditText = findViewById(R.id.etValue)

        data.addAll(listSpend())

        adapter = SpendAdapter(this, data)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val add: ImageButton = findViewById(R.id.imageButton)
        add.setOnClickListener(){
            if (rv.visibility == View.VISIBLE){
                rv.visibility = View.GONE
                addForm.visibility = View.VISIBLE
                submit.setOnClickListener() {
                    if (etName.text.toString().equals("") && etValue.text.toString().equals("")){
                        Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }else {
                        val spending = SpendModel()
                        spending.name = etName.text.toString()
                        spending.value = etValue.text.toString()
                        data.add(spending)
                        etValue.text.clear()
                        etName.text.clear()
                        adapter.notifyItemInserted(data.size -1)
                        rv.visibility = View.VISIBLE
                        addForm.visibility = View.GONE
                    }
                }
            } else {
                rv.visibility = View.VISIBLE
                addForm.visibility = View.GONE
            }
        }


    }

    private fun listSpend(): List<SpendModel>{
        val data = ArrayList<SpendModel>()
        val listNama: Array<String> = resources.getStringArray(R.array.song)
        val listValue: Array<String> = resources.getStringArray(R.array.artis)
        for (i in listNama.indices){
            val spending = SpendModel()
            spending.name = listNama[i]
            spending.value = listValue[i]
            data.add(spending)
        }
        return data
    }


}
