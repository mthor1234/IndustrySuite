package thornton.mj.com.industrysuite.adapters

/**
 * Created by mitchthornton on 8/22/18.
 */
import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.alertmanager_detail.view.*
import thornton.mj.com.industrysuite.R

class AlertManagerAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.alertmanager_detail, parent, false))
    }

    // Binds each response in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tv_response?.text = items.get(position)

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    // Holds the TextView that will add each animal to
    val tv_response = view.tv_alertmanager_detail

    init{
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val manager = (v.context as Activity).fragmentManager

        manager.popBackStack()

    }


}