package makesolution.relatorica.sqlite.Network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.androidnetworking.common.Priority
import makesolution.relatorica.sqlite.Response.EntregaResponse

class SQLiteApi {
    companion object{
        var baseUrl = "https://analytics.consorciohbo.com.pe/servicio/api/entregas"
        fun GetEntrega(url: String,
                       responseHandler: (EntregaResponse?) -> Unit,
                       errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .setTag("SQLiteApp")
                .build()
                .getAsObject(EntregaResponse::class.java,
                    object : ParsedRequestListener<EntregaResponse> {
                        override fun onResponse(response: EntregaResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}