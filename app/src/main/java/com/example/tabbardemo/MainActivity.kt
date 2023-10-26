package com.example.tabbardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable


	class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				Surface(color = MaterialTheme.colors.background) {
					val badgeValue = remember { mutableStateOf(0f) }
					val selectedTab = remember { mutableStateOf(0) }

					Column {
						when (selectedTab.value) {
							0 -> SliderView(badgeValue)
							1 -> BadgeView(badgeValue.value.toInt())
						}

						BottomNavigation {
							BottomNavigationItem(
								icon = { Icon(Icons.Filled.Settings, contentDescription = "Slider") },
								label = { Text("Slider") },
								selected = selectedTab.value == 0,
								onClick = { selectedTab.value = 0 }
							)

							BottomNavigationItem(
								icon = {
									Icon(Icons.Filled.Notifications, contentDescription = "Badge")
									if (badgeValue.value > 0) {
										Text(
											text = badgeValue.value.toInt().toString(),
											color = Color.White,
											modifier = Modifier.padding(start = 4.dp, top = 4.dp)
										)
									}
								},
								label = { Text("Badge") },
								selected = selectedTab.value == 1,
								onClick = { selectedTab.value = 1 }
							)
						}
					}
				}
			}
		}
	}
}

@Composable
fun SliderView(badgeValue: androidx.compose.runtime.MutableState<Float>) {
	Column(modifier = Modifier.padding(16.dp)) {
		Text("Set Badge Value", style = MaterialTheme.typography.h4)
		Slider(value = badgeValue.value, onValueChange = { newValue -> badgeValue.value = newValue }, valueRange = 0f..100f, steps = 100)
		Text("Selected badge value: ${badgeValue.value.toInt()}", style = MaterialTheme.typography.h5)
	}
}

@Composable
fun BadgeView(badgeValue: Int) {
	Column(modifier = Modifier.padding(16.dp)) {
		Text("Badge Value", style = MaterialTheme.typography.h4)
		Text(badgeValue.toString(), style = MaterialTheme.typography.h1)
	}
}
