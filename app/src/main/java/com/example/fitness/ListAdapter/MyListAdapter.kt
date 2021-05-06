package com.example.fitness.ListAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fitness.R

class MyListAdapter(var mCtx: Context, var resource:Int, var items:List<Model>)
    : BaseAdapter(){




    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        val imageView : ImageView = view.findViewById(R.id.icon)
        var textView : TextView = view.findViewById(R.id.title)
        var textView1 = view.findViewById<TextView>(R.id.desc)


        var person : Model = items[position]

        imageView.setImageDrawable(mCtx.resources.getDrawable(person.photo))
        textView.text = person.title
        textView1.text = person.desc


        return view
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}