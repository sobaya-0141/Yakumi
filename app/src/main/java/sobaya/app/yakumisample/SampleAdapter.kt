package sobaya.app.yakumisample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sobaya.app.yakumi.Yakumi

class SampleAdapter : RecyclerView.Adapter<ViewHolder>(), Yakumi.YakumiAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = 100

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun setYakumiText(position: Int) = position.toString()
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
