package com.revature.contentprovider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import com.revature.contentprovider.view.ui.theme.ContentProviderTheme

//Database setup
//entity setup
//DAO setup

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentProviderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UI()
                }
            }
        }
    }
}
@SuppressLint("Range")
@Composable
fun UI() {
    var context= LocalContext.current
    Column() {
        var name by remember { mutableStateOf("") }
        var result  by remember{ mutableStateOf("")}

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Button(onClick = {
            val values = ContentValues()
            values.put(RevatureContentProvider.name, name)
            context.contentResolver.insert(RevatureContentProvider.CONTENT_URI, values)
            Toast.makeText(context, "New Record is Inserted", Toast.LENGTH_LONG).show()

        }) {

            Text(text = "Add New Record")
        }
        Button(onClick = {
            val cursor = context.contentResolver.query(RevatureContentProvider.CONTENT_URI, null, null, null, null)
            if(cursor!!.moveToFirst()) {
                val stringBuilder = StringBuilder()
                while(!cursor.isAfterLast) {
                    result = "${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}"
                    cursor.moveToNext()
                }
                Log.d("Data", "$result")
            } else {
                Log.d("Data", "No Records Found")
            }

        }) {
            Text(text = "Show Records")
        }
    }
}
