package com.example.clerkapplication

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clerkapplication.util.ErrorLogger
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {


    val contentUrl = "content://com.example.notsolowbudgethotel.provider.GuestProvider/guests"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onResume() {
        super.onResume()
        getCurrentGuests()

    }

    private fun getCurrentGuests() {

        val uri = Uri.parse(contentUrl)
        var cursor: Cursor?
        val stringBuilder = StringBuilder()

        try {
            ErrorLogger.logError(Throwable("Attempting"))
            cursor = contentResolver
                .query(uri, null, null, null, null)

            cursor?.let { myValues ->
                if (myValues.count == 0) {
                    my_textview.text = "No Hotel Guests"
                } else {
                    myValues.moveToFirst()
                    while (myValues.moveToNext()) {
                        stringBuilder.append(
                            "${myValues.getString(myValues.getColumnIndex("GuestName"))} ${myValues.getString(
                                myValues.getColumnIndex("RoomNumber")
                            )} ${myValues.getString(myValues.getColumnIndex("LengthOfStay"))}\n"
                        )
                    }
                    my_textview.text = stringBuilder.toString()

                }

                myValues.close()
            } ?: {
                my_textview.text = "Empty List"
                ErrorLogger.logError(Throwable("Resolver was null."))
            }()
        } catch (throwable: Throwable) {
            ErrorLogger.logError(throwable)
        }

    }


}
