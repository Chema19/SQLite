package makesolution.relatorica.sqlite.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.direccionTextView
import kotlinx.android.synthetic.main.activity_main.view.ordenTextView
import kotlinx.android.synthetic.main.activity_main.view.ratingTextView
import kotlinx.android.synthetic.main.cardview_main.view.*
import makesolution.relatorica.sqlite.Activity.SecondActivity
import makesolution.relatorica.sqlite.Model.EntregaModel
import makesolution.relatorica.sqlite.R

class MainAdapter (var entregas :ArrayList<EntregaModel>, val context: Context): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.cardview_main, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return entregas.size
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val entrega = entregas.get(position)
        holder.updateFrom(context, position, entrega)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ordenTextView = view.ordenTextView
        val direccionTextView = view.direccionTextView
        val ratingTextView = view.ratingTextView
        var ratingCardView = view.ratingCardView

        fun updateFrom(context: Context, position: Int, entrega: EntregaModel) {
            ordenTextView.text = "N° " + position.toString()
            direccionTextView.text = entrega.Address
            ratingTextView.text = entrega.Rating.toString()
            ratingCardView.setOnClickListener{view ->
                context.startActivity(
                    Intent(context, SecondActivity::class.java)
                        .putExtras(entrega.toBundle()))
            }
        }
    }
}