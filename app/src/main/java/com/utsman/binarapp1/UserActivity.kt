package com.utsman.binarapp1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.utsman.binarapp1.data.User

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val tvUserBundle: TextView = findViewById(R.id.tv_ngambil_bundle)
        val tvSerializable : TextView = findViewById(R.id.tv_serializable)
        val tvSerializableHasmap : TextView = findViewById(R.id.tv_serializable_hasmap)
        val tvParcelable : TextView = findViewById(R.id.tv_parcelable)
        val parcelableList: TextView = findViewById(R.id.tv_parcel_list)

         //ini nerima bundle biasa
        val bundle = intent.extras
        val name = bundle?.getString("name")
        val ageBund = bundle?.getInt("age")
        tvUserBundle.text = name + " " + ageBund.toString()


         /*ini cara ngambil serializable*/
        val user = intent.getSerializableExtra("user") as User // ini disebut casting
        val nameSerializable = user.name
        val age = user.age

        tvSerializable.text = "nama: $nameSerializable, umur: $age"

        /* ini cara ngambil serializable hasmap */
        /*val userSerializableHasmap = intent.getSerializableExtra("map_data") as HashMap<String, Any> // ini disebut casting
        val nameSerializableHasmap = userSerializableHasmap.getValue("name") as String
        val ageSerializableHasmap = userSerializableHasmap.getValue("age") as Int

        tvSerializableHasmap.text = "nama: $nameSerializableHasmap, umur: $ageSerializableHasmap"*/

        /* ini cara ngambil parcel */
        /*val address = intent.getParcelableExtra<Address>("address")
        val city = address?.city
        tvParcelable.text = city*/

        /* ini cara ngambil parcel list */
       /* val addressParcelList = intent.getParcelableArrayListExtra<Address>("addresses")
        val cityParcelist = addressParcelList?.map { add -> add.city }
        parcelableList.text = cityParcelist.toString()*/
    }
}