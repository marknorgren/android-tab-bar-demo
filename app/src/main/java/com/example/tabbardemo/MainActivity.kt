package com.example.tabbardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Favorite

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

						Spacer(modifier = Modifier.weight(1f))

						// Moved BottomNavigation to bottom
						BottomNavigation {
							BottomNavigationItem(
								icon = { Icon(Icons.Filled.Settings, contentDescription = "Slider") },
								label = { Text("Slider") },
								selected = selectedTab.value == 0,
								onClick = { selectedTab.value = 0 }
							)
							BottomNavigationItem(
								icon = {
									BadgedBox(badge = { Badge { Text(badgeValue.value.toInt().toString()) } }) {
										Icon(
											Icons.Filled.Favorite,
											contentDescription = "Favorite"
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
	Column(
		modifier = Modifier
			.padding(24.dp)  // Padding for the entire column
	) {
		Text("Set Badge Value", style = MaterialTheme.typography.h4)
		Slider(
			value = badgeValue.value,
			onValueChange = { newValue -> badgeValue.value = newValue },
			valueRange = 0f..100f,
			steps = 100,
			modifier = Modifier.padding(bottom = 16.dp)  // Added padding to the Slider
		)
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
