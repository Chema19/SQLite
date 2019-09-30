package makesolution.relatorica.sqlite.Model

import android.os.Bundle

data class EntregaModel (var Id: Int = 0,
                         var Source: String? = null,
                         var Address: String? = null,
                         var Rating: String? = null,
                         var Createdon: String? = null){

    companion object{
        fun from(bundle: Bundle): EntregaModel{
            return EntregaModel(
                bundle.getInt("id")!!,
                bundle.getString("source")!!,
                bundle.getString("address")!!,
                bundle.getString("rating")!!,
                bundle.getString("createdon")!!
            )
        }
    }

    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("id",Id)
        bundle.putString("source",Source)
        bundle.putString("address",Address)
        bundle.putString("rating",Rating)
        bundle.putString("createdon",Createdon)

        return bundle
    }
}