package makesolution.relatorica.sqlite.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import makesolution.relatorica.sqlite.DataAccess.DBAccess
import makesolution.relatorica.sqlite.Model.EntregaModel
import makesolution.relatorica.sqlite.R

class SecondActivity : AppCompatActivity() {

    internal lateinit var  db: DBAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent ?: return
        //val entrega =  EntregaModel.from(intent.extras)
        val prueba  = 0
        saveButton.setOnClickListener{view ->

            val entrega =  EntregaModel.from(intent.extras!!)
            var puntaje = puntajeEditText.text
            val entregaModel = EntregaModel(entrega!!.Id,
                entrega!!.Source,
                entrega!!.Address,
                puntaje!!.toString(),
                entrega!!.Createdon)

            //entregaModel.Id = entrega!!.Id
            //entregaModel.Createdon = entrega!!.Createdon
            //entregaModel.Address = entrega!!.Address
            //entregaModel.Rating = puntaje!!.toString()
            //entregaModel.Source = entrega!!.Source

            db.updateEntrega(entregaModel)
            val intento = Intent(this, MainActivity::class.java)
            startActivity(intento)
        }

    }
}
