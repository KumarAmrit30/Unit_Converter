package com.example.unitconverter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.floor
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("0.0") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iConversionRate by remember{ mutableDoubleStateOf(1.00) }
    var oConversionRate by remember{ mutableDoubleStateOf(1.00) }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }

    val customTextStyle=TextStyle(
        fontFamily = FontFamily.Monospace,
        color = colorResource(id = R.color.lightYellow),
        fontSize = 50.sp
    )

    val customResultStyle=TextStyle(
        fontFamily = FontFamily.Monospace,
        color = Color.White,
        fontSize = 36.sp
    )

    fun convertUnits(){
//        ?: -> elvis operator ( somewhat like ternary operator )
        val inputValueDouble=inputValue.toDoubleOrNull() ?: 0.0
        val result=(inputValueDouble * oConversionRate * 100.0 / iConversionRate)/ 100.0
        val truncatedResult = String.format("%.6f",result).toDouble()
        outputValue=truncatedResult.toString()

    }

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//    Here all the UI element will be stacked below each other
        Text("Unit Converter",
            style=customTextStyle)
        Spacer(modifier=Modifier.height(20.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertUnits()
        }, label = {Text("Enter value ")})
        Spacer(modifier=Modifier.height(20.dp))
        Row {
            //    Here all the UI element will be stacked beside each other
            Box{
//                Input Button
                Button(onClick = { iExpanded=true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false })
                {
                    DropdownMenuItem(text = { Text("Kilometers") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Kilometers"
                            iConversionRate= 1000.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Meters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Meters"
                            iConversionRate= 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeters"
                            iConversionRate=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Millimeters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimeters"
                            iConversionRate=0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Mile") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Mile"
                            iConversionRate=0.000621371
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Yard") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Yard"
                            iConversionRate=1.094
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            iConversionRate=0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(text = { Text("Inches") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Inches"
                            iConversionRate=39.37
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
//                Output Button
                Button(onClick = { oExpanded=true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false })
                {
                    DropdownMenuItem(text = { Text("Kilometers") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Kilometers"
                            oConversionRate=1000.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Meters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Meters"
                            oConversionRate=1.0
                            convertUnits() }
                    )
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimeters"
                            oConversionRate=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Millimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Millimeters"
                            oConversionRate=0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Mile") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Mile"
                            oConversionRate=0.000621371
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(text = { Text("Yard") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Yard"
                            oConversionRate=1.094
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            oConversionRate=0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(text = { Text("Inches") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Inches"
                            oConversionRate=39.37
                            convertUnits()}
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if(inputValue==""){
            Text("Result: $outputValue",
                style=customResultStyle)
        }else Text("Result: $outputValue $outputUnit")
        Spacer(modifier = Modifier.height(98.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}