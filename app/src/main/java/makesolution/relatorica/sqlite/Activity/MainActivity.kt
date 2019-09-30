package makesolution.relatorica.sqlite.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_main.*
import makesolution.relatorica.sqlite.Adapter.MainAdapter
import makesolution.relatorica.sqlite.DataAccess.DBAccess
import makesolution.relatorica.sqlite.Model.EntregaModel
import makesolution.relatorica.sqlite.Network.SQLiteApi
import makesolution.relatorica.sqlite.R
import makesolution.relatorica.sqlite.Response.EntregaResponse
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {


    internal lateinit var  db:DBAccess
    lateinit var entregaRecyclerView: RecyclerView
    lateinit var entregas: ArrayList<EntregaModel>
    lateinit var entregaAdapter: MainAdapter
    lateinit var entregaLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBAccess(this)


        entregaRecyclerView = entregasRecyclerView
        entregas = ArrayList()

        refreshData()

        addButton.setOnClickListener{
            var url: String = SQLiteApi.baseUrl

            SQLiteApi.GetEntrega(url,
                { response -> handleResponse(response) },
                { error -> handleError(error) })
        }
    }
    private fun handleResponse(response: EntregaResponse?){
        val entrega = EntregaModel()

        entrega.Id = parseInt(response!!.id.toString())
        entrega.Source = response!!.source
        entrega.Rating = response!!.rating
        entrega.Address = response!!.address
        entrega.Createdon = response!!.createdon

        db.addEntrega(entrega)
        refreshData()
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }

    private fun refreshData()
    {
        entregas = db.allEntrega
        entregaAdapter = MainAdapter(entregas,this)
        entregaLayoutManager = GridLayoutManager(this,1)
        entregaRecyclerView.adapter = entregaAdapter
        entregaRecyclerView.layoutManager = entregaLayoutManager

    }
}
